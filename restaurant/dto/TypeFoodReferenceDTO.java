package br.com.gofood.gofood.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeFoodReferenceDTO {
    private String id;
    private String name;

    public TypeFoodReferenceDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

