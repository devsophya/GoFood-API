package br.com.gofood.gofood.promotion.usecase;

import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.repositories.PromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetValidPromotionsUseCase  {
    private final PromotionRepository promotionRepository;

    public GetValidPromotionsUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> execute() {
        LocalDate today = LocalDate.now();
        return promotionRepository.findAllValidPromotions(today);
    }
}

