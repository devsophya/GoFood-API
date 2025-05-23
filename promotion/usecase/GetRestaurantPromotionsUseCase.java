package br.com.gofood.gofood.promotion.usecase;

import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.repositories.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetRestaurantPromotionsUseCase {

    private final PromotionRepository promotionRepository;

    public GetRestaurantPromotionsUseCase(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Page<Promotion> execute(String idRestaurant, int page, String search) {
        Pageable pageable = PageRequest.of(page, 8);

        if (search == null || search.isEmpty()) {
            return promotionRepository.findByIdRestaurant(idRestaurant, pageable);
        } else {
            return promotionRepository.findByIdRestaurantAndNameContainingIgnoreCase(idRestaurant, search, pageable);
        }
    }
}
