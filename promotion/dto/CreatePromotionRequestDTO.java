package br.com.gofood.gofood.promotion.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePromotionRequestDTO {

    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Discount is required.")
    private Double discount;

    @NotNull(message = "Start promotion date is required.")
    @FutureOrPresent(message = "Start promotion date must be today or in the future.")
    private LocalDate startPromotion;

    @NotNull(message = "End promotion date is required.")
    @FutureOrPresent(message = "End promotion date must be today or in the future.")
    private LocalDate endPromotion;

    private String imageUrl;
}
