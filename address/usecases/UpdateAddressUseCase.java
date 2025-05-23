package br.com.gofood.gofood.address.usecases;

import br.com.gofood.gofood.address.collections.AddressCollection;
import br.com.gofood.gofood.address.dto.AddressResponseDTO;
import br.com.gofood.gofood.address.dto.AddressUpdateRequestDTO;
import br.com.gofood.gofood.address.repositories.AddressClientRepository;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateAddressUseCase {

    private final AddressClientRepository addressClientRepository;
    private final ClientRepository clientRepository;


    public AddressCollection execute(String clientId, AddressUpdateRequestDTO updateRequest) {
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (!client.getAddressIds().contains(updateRequest.getId())) {
            throw new RuntimeException("Address does not belong to this client");
        }

        AddressCollection address = addressClientRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setZipcode(updateRequest.getZipcode());
        address.setStreet(updateRequest.getStreet());
        address.setNumber(updateRequest.getNumber());
        address.setNeighborhood(updateRequest.getNeighborhood());
        address.setCity(updateRequest.getCity());
        address.setState(updateRequest.getState());

        return addressClientRepository.save(address);
    }
}
//S