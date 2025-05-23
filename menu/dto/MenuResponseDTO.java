package br.com.gofood.gofood.menu.dto;

import br.com.gofood.gofood.menu.entities.MenuCollection.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuResponseDTO {
    private String id;
    private String name;
    private String description;
    private String foodImage;
    private Double price;
    private Category foodCategory;
    private Boolean available;
    private String restaurantId;

    public MenuResponseDTO(String id, String name, String description, String foodImage, Double price,
                           Category foodCategory, Boolean available, String restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.foodImage = foodImage;
        this.price = price;
        this.foodCategory = foodCategory;
        this.available = available;
        this.restaurantId = restaurantId;
    }
}
//S
