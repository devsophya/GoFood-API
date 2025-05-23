package br.com.gofood.gofood.restaurant.repositories;

import br.com.gofood.gofood.restaurant.entities.TypeFoodCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TypeFoodRepository extends MongoRepository<TypeFoodCollection, String> {
    Optional<TypeFoodCollection> findByName(String name);
}
//S