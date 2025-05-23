package br.com.gofood.gofood.client.repositories;

import br.com.gofood.gofood.client.entities.ClientCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<ClientCollection, String> {
    Optional<ClientCollection> findByCpf(String cpf);
    Optional<ClientCollection> findByEmail(String email);
}
//S
