package br.com.gofood.gofood.favorites.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFoodFavoriteRequestDTO {
    private String idFoodMenu;
    private String idRestaurant;
}

