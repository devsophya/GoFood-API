package br.com.gofood.gofood.address.repositories;

import br.com.gofood.gofood.address.collections.AddressCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressClientRepository extends MongoRepository<AddressCollection, String> {
    List<AddressCollection> findByIdIn(List<String> ids);
}
// S