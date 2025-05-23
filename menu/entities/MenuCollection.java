package br.com.gofood.gofood.menu.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "menus")
@Getter
@Setter
public class MenuCollection {

    @Id
    private String id;

    private String idRestaurant;

    @NotBlank(message = "The name field is mandatory")
    @NotNull(message = "The name of the food must not be blank")
    @Schema(example = "Lasagna", requiredMode = Schema.RequiredMode.REQUIRED, description = "Food name")
    private String name;

    @Schema(example = "https://firebasestorage.googleapis.com/v0/b/seu-projeto.appspot.com/o/imagens%2Frestaurante.jpg?alt=media",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "URL of the food image stored on Cloudinary")
    private String foodImage;

    @NotBlank(message = "The description field is mandatory")
    @NotNull(message = "The description of the food must not be blank")
    @Schema(example = "Flavors that delight", requiredMode = Schema.RequiredMode.REQUIRED, description = "Brief description of the food" )
    private String description;

    @NotNull(message = "The price must not be null")
    @Positive(message = "The price must be greater than zero")
    @Schema(example = "25.99", requiredMode = Schema.RequiredMode.REQUIRED, description = "Price of the food")
    private Double price;

    @NotNull(message = "The food category is mandatory")
    @Schema(example = "FOOD", requiredMode = Schema.RequiredMode.REQUIRED, description = "Category of the food (FOOD, DESSERT, DRINK)")
    private Category foodCategory;

    @Schema(example = "true", requiredMode = Schema.RequiredMode.REQUIRED, description = "Indicates whether the dish is available")
    private boolean available;

    @NotBlank(message = "The restaurant ID is mandatory")
    private String restaurantId;

    public enum Category {
        FOOD, DESSERT, DRINK;
    }

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    private LocalDateTime deletedAt;

}
//S
