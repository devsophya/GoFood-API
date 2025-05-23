package br.com.gofood.gofood.restaurant.dto;

import br.com.gofood.gofood.restaurant.entities.AddressCollection;
import br.com.gofood.gofood.restaurant.entities.TypeFoodCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRestaurantResponseDTO {
    private String name;
    private String phone;
    private AddressCollection addressCollection;
    private List<TypeFoodCollection> typeFoodCollections;
}
