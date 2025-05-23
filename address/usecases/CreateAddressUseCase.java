package br.com.gofood.gofood.address.usecases;

import br.com.gofood.gofood.address.collections.AddressCollection;
import br.com.gofood.gofood.address.repositories.AddressClientRepository;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CreateAddressUseCase {

    private final AddressClientRepository addressClientRepository;
    private final ClientRepository clientRepository;
    private static final int MAX_ADDRESSES = 5;


    public AddressCollection execute(AddressCollection addressCollection, String clientId) {
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (client.getAddressIds().size() >= MAX_ADDRESSES) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The customer already has 5 addresses registered. To add a new one, delete an existing address. ");
        }

        AddressCollection savedAddress = addressClientRepository.save(addressCollection);
        client.getAddressIds().add(savedAddress.getId());
        clientRepository.save(client);

        return savedAddress;
    }
}

