package br.com.gofood.gofood.favorites.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FavoritesResponseDTO {
    private List<RestaurantFavoriteResponseDTO> restaurantFavorites;
    private List<FoodFavoriteResponseDTO> foodFavorites;
}
