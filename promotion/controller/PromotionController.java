package br.com.gofood.gofood.promotion.controller;

import br.com.gofood.gofood.promotion.dto.CreatePromotionRequestDTO;
import br.com.gofood.gofood.promotion.dto.UpdatePromotionRequestDTO;
import br.com.gofood.gofood.promotion.entities.Promotion;
import br.com.gofood.gofood.promotion.usecase.CreatePromotionUseCase;
import br.com.gofood.gofood.promotion.usecase.DeletePromotionUseCase;
import br.com.gofood.gofood.promotion.usecase.GetRestaurantPromotionsUseCase;
import br.com.gofood.gofood.promotion.usecase.UpdatePromotionUseCase;
import br.com.gofood.gofood.restaurant.usecases.CloudinaryUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurant/promotion")
@PreAuthorize("hasRole('RESTAURANT')")
public class PromotionController {

    private final CreatePromotionUseCase createPromotionUseCase;
    private final UpdatePromotionUseCase updatePromotionUseCase;
    private final GetRestaurantPromotionsUseCase getRestaurantPromotionsUseCase;
    private final DeletePromotionUseCase deletePromotionUseCase;
    private final CloudinaryUseCase cloudinaryUseCase;


    public PromotionController(CreatePromotionUseCase createPromotionUseCase,
                               UpdatePromotionUseCase updatePromotionUseCase,
                               GetRestaurantPromotionsUseCase getRestaurantPromotionsUseCase,
                               DeletePromotionUseCase deletePromotionUseCase, CloudinaryUseCase cloudinaryUseCase) {
        this.createPromotionUseCase = createPromotionUseCase;
        this.updatePromotionUseCase = updatePromotionUseCase;
        this.getRestaurantPromotionsUseCase = getRestaurantPromotionsUseCase;
        this.deletePromotionUseCase = deletePromotionUseCase;
        this.cloudinaryUseCase = cloudinaryUseCase;
    }

    @PostMapping(value = "/create/{restaurantId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createPromotion(
            @PathVariable String restaurantId,
            @RequestPart("promotion") @Valid @Validated CreatePromotionRequestDTO promotionDTO,
            @RequestPart("file") MultipartFile imageFile) throws IOException {
        String imageUrl = cloudinaryUseCase.uploadImage(imageFile);
        var promotion = createPromotionUseCase.execute(promotionDTO, restaurantId, imageUrl);
        return ResponseEntity.ok().body(promotion);
    }


    @PutMapping(value = "/update/{restaurantId}/{promotionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updatePromotion(
            @PathVariable String restaurantId,
            @PathVariable String promotionId,
            @RequestPart("promotion") @Valid @Validated UpdatePromotionRequestDTO promotionDTO,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = cloudinaryUseCase.uploadImage(imageFile);
        }
        var updatedPromotion = updatePromotionUseCase.execute(restaurantId, promotionId, promotionDTO, imageUrl);
        return ResponseEntity.ok().body(updatedPromotion);
    }

    @GetMapping("/get/{restaurantId}")
    public ResponseEntity<Page<Promotion>> getPromotions(@PathVariable String restaurantId,
                                                         @RequestParam int page,
                                                         @RequestParam(required = false) String search) {
        Page<Promotion> promotions = getRestaurantPromotionsUseCase.execute(restaurantId, page, search);
        return ResponseEntity.ok(promotions);
    }

    @DeleteMapping("/delete/{restaurantId}/{promotionId}")
    public ResponseEntity<Map<String, String>> deletePromotion(@PathVariable String restaurantId,
                                                               @PathVariable String promotionId) {
        deletePromotionUseCase.execute(restaurantId, promotionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Promotion successfully deleted.");
        return ResponseEntity.ok(response);
    }
}