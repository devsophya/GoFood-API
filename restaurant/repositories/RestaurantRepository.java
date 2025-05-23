package br.com.gofood.gofood.restaurant.repositories;

import br.com.gofood.gofood.restaurant.entities.RestaurantCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<RestaurantCollection, String> {
    Optional<RestaurantCollection> findByCnpj(String cnpj);
    Optional<RestaurantCollection> findByEmail(String email);

    @Query(value = "{ 'status': true }", fields = "{ 'image': 1, 'name': 1, 'typeFoodCollections': 1 }")
    Page<RestaurantCollection> findAllProjected(Pageable pageable);
}
//S