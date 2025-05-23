package br.com.gofood.gofood.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthRestaurantResponseDTO {
    private String access_token;
    private String expires_in;
}
//S

