package br.com.gofood.gofood.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DeleteRestaurantResponseDTO {
    private String cnpj;
    private Boolean status;
    private String message;
    private LocalDateTime deletedAt = LocalDateTime.now();
}
//S