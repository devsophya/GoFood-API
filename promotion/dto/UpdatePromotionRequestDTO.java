package br.com.gofood.gofood.promotion.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePromotionRequestDTO {

    private String name;

    @PositiveOrZero(message = "O desconto deve ser um número positivo ou zero.")
    private double discount;

    @FutureOrPresent(message = "A data de início da promoção deve ser hoje ou uma data futura.")
    private LocalDate startPromotion;

    @FutureOrPresent(message = "A data de término da promoção deve ser hoje ou uma data futura.")
    private LocalDate endPromotion;
}
