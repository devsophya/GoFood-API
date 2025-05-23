package br.com.gofood.gofood.favorites.entities;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "foodFavorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodFavoritesCollection {

    @Id
    private String id;

    private String clientId;
    private String idFoodMenu;
    private String idRestaurant;

}
//S