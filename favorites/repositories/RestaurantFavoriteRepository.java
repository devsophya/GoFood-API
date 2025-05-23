package br.com.gofood.gofood.favorites.repositories;

import br.com.gofood.gofood.favorites.entities.RestaurantFavoriteCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantFavoriteRepository extends MongoRepository<RestaurantFavoriteCollection, String> {
    List<RestaurantFavoriteCollection> findAllByClientId(String clientId);
    Optional<RestaurantFavoriteCollection> findByClientIdAndIdRestaurant(String clientId, String idRestaurant);
}