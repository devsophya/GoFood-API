package br.com.gofood.gofood.address.usecases;

import br.com.gofood.gofood.address.dto.AddressDeleteRequestDTO;
import br.com.gofood.gofood.address.repositories.AddressClientRepository;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteAddressUseCase {

    private final AddressClientRepository addressClientRepository;
    private final ClientRepository clientRepository;

    public void execute(AddressDeleteRequestDTO request) {
        ClientCollection client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if (!client.getAddressIds().contains(request.getAddressId())) {
            throw new RuntimeException("Address does not belong to this client");
        }

        client.getAddressIds().remove(request.getAddressId());
        clientRepository.save(client);

        addressClientRepository.deleteById(request.getAddressId());
    }
}
//S