package br.com.gofood.gofood.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllRestaurantsResponseDTO {
    private String image;
    private String name;
    private List<String> typeFoods;

    public GetAllRestaurantsResponseDTO(String image, String name, List<String> typeFoods) {
        this.image = image;
        this.name = name;
        this.typeFoods = typeFoods;
    }
}
//S
