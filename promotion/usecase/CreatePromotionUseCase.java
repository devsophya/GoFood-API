package br.com.gofood.gofood.promotion.usecase;

import br.com.gofood.gofood.promotion.dto.CreatePromotionRequestDTO;
import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.repositories.PromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CreatePromotionUseCase {

    private final PromotionRepository promotionRepository;

    public CreatePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion execute(CreatePromotionRequestDTO request, String idRestaurant, String promotionImageUrl) {
        validateDates(request.getStartPromotion(), request.getEndPromotion());

        Promotion promotion = Promotion.builder()
                .idRestaurant(idRestaurant)
                .name(request.getName())
                .discount(request.getDiscount())
                .startPromotion(request.getStartPromotion())
                .endPromotion(request.getEndPromotion())
                .promotionImage(promotionImageUrl)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return promotionRepository.save(promotion);
    }

    private void validateDates(LocalDate start, LocalDate end) {
        LocalDate today = LocalDate.now();

        if (start == null) {
            throw new IllegalArgumentException("The promotion start date is mandatory.");
        }

        if (start.isBefore(today)) {
            throw new IllegalArgumentException("The start date of the promotion cannot be earlier than the current date.");
        }

        if (end != null && end.isBefore(start)) {
            throw new IllegalArgumentException("The end date of the promotion cannot be earlier than the start date.");
        }
    }
}