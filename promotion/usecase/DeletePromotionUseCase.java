package br.com.gofood.gofood.promotion.usecase;

import br.com.gofood.gofood.exceptions.PromotionNotBelongToRestaurantException;
import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.repositories.PromotionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletePromotionUseCase {

    private final PromotionRepository promotionRepository;

    public DeletePromotionUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public void execute(String idRestaurant, String idPromotion) {
        Promotion promotion = promotionRepository.findById(idPromotion)
                .orElseThrow(() -> new EntityNotFoundException("Promotion not found."));

        if (!promotion.getIdRestaurant().equals(idRestaurant)) {
            throw new PromotionNotBelongToRestaurantException();
        }

        promotionRepository.deleteById(idPromotion);
    }
}