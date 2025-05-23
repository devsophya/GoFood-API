package br.com.gofood.gofood.promotion.usecase;

import br.com.gofood.gofood.promotion.dto.UpdatePromotionRequestDTO;
import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.repositories.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UpdatePromotionUseCase {

    private final PromotionRepository promotionRepository;

    public UpdatePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion execute(String restaurantId, String idPromotion, UpdatePromotionRequestDTO request, String promotionImageUrl) {
        Promotion promotion = promotionRepository.findById(idPromotion)
                .orElseThrow(() -> new EntityNotFoundException("Promoção não encontrada"));

        validateDates(request.getStartPromotion(), request.getEndPromotion());
        promotion.setName(request.getName() != null ? request.getName() : promotion.getName());
        promotion.setDiscount(request.getDiscount());
        promotion.setStartPromotion(request.getStartPromotion());
        promotion.setEndPromotion(request.getEndPromotion());
        if (promotionImageUrl != null) {
            promotion.setPromotionImage(promotionImageUrl);
        }
        promotion.setUpdatedAt(LocalDateTime.now());
        return promotionRepository.save(promotion);
    }

    private void validateDates(LocalDate start, LocalDate end) {
        LocalDate today = LocalDate.now();
        if (start.isBefore(today)) {
            throw new IllegalArgumentException("A data de início não pode ser anterior à data atual.");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        }
    }
}