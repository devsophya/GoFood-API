package br.com.gofood.gofood.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import br.com.gofood.gofood.menu.entities.MenuCollection.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestDTO {

    @NotBlank(message = "The name field is mandatory")
    private String name;

    @NotBlank(message = "The description field is mandatory")
    private String description;

    @NotBlank(message = "The food image URL is mandatory")
    private String foodImage;

    @NotNull(message = "The price must not be null")
    @Positive(message = "The price must be greater than zero")
    private Double price;

    @NotNull(message = "The food category is mandatory")
    private Category foodCategory;

    @NotNull(message = "The availability status is required")
    private Boolean available = true;

    @NotBlank(message = "The restaurant ID is mandatory")
    private String restaurantId;
}
//S
