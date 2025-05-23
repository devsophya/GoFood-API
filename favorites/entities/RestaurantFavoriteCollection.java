package br.com.gofood.gofood.favorites.entities;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "restaurantFavorites")
public class RestaurantFavoriteCollection {

    @Id
    private String id;

    private String clientId;
    private String idRestaurant;
}