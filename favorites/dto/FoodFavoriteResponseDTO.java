package br.com.gofood.gofood.favorites.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodFavoriteResponseDTO {
    private String id;
    private String idFoodMenu;
    private String name;
    private String description;
    private Double price;
    private String idRestaurant;
    private Double averageRating;
}
