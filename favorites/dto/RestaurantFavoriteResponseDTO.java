package br.com.gofood.gofood.favorites.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantFavoriteResponseDTO {
    private String id;
    private String idRestaurant;
    private String name;
    private String image;
    private String description;
}
