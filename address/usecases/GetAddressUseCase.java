package br.com.gofood.gofood.address.usecases;

import br.com.gofood.gofood.address.collections.AddressCollection;
import br.com.gofood.gofood.address.dto.AddressResponseDTO;
import br.com.gofood.gofood.address.repositories.AddressClientRepository;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetAddressUseCase {

    private final AddressClientRepository addressClientRepository;
    private final ClientRepository clientRepository;


    public List<AddressResponseDTO> execute(String clientId) {
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        List<AddressCollection> addresses = addressClientRepository.findByIdIn(client.getAddressIds());

        return addresses.stream().map(AddressResponseDTO::new).collect(Collectors.toList());
    }
}
