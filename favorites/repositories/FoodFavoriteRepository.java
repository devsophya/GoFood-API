package br.com.gofood.gofood.favorites.repositories;

import br.com.gofood.gofood.favorites.entities.FoodFavoritesCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodFavoriteRepository extends MongoRepository<FoodFavoritesCollection, String> {
    List<FoodFavoritesCollection> findAllByClientId(String clientId);
    Optional<FoodFavoritesCollection> findByClientIdAndIdFoodMenu(String clientId, String idFoodMenu);
}
