package br.com.gofood.gofood.restaurant.repositories;

import br.com.gofood.gofood.restaurant.entities.AddressCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<AddressCollection, String> {
    Optional<AddressCollection> findById(String id);
}
//S