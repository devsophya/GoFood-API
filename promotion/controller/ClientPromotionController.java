package br.com.gofood.gofood.promotion.controller;

import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.usecase.GetValidPromotionsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/promotion")
@PreAuthorize("hasRole('CLIENT')")
public class ClientPromotionController {

    private final GetValidPromotionsUseCase getValidPromotionsUseCase;

    public ClientPromotionController(GetValidPromotionsUseCase getValidPromotionsUseCase) {
        this.getValidPromotionsUseCase = getValidPromotionsUseCase;
    }

    @GetMapping("/{clientId}/get")
    public ResponseEntity<List<Promotion>> getValidPromotions(@PathVariable String clientId) {
        List<Promotion> promotions = getValidPromotionsUseCase.execute();
        return ResponseEntity.ok(promotions);
    }
}
