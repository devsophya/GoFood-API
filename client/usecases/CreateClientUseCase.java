package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.address.repositories.AddressClientRepository;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.exceptions.ClientFoundException;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateClientUseCase {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private AddressClientRepository addressClientRepository;

    public ClientCollection execute(ClientCollection clientCollection) {
        this.clientRepository.findByCpf(clientCollection.getCpf())
                .ifPresent(c -> { throw new ClientFoundException(); });

        clientCollection.setPassword(passwordEncoder.encode(clientCollection.getPassword()));

        if (clientCollection.getAddresses() != null && !clientCollection.getAddresses().isEmpty()) {
            List<String> savedAddressIds = clientCollection.getAddresses().stream()
                    .map(address -> addressClientRepository.save(address).getId())
                    .collect(Collectors.toList());

            clientCollection.setAddressIds(savedAddressIds);
            clientCollection.setAddresses(null);
        }

        return this.clientRepository.save(clientCollection);
    }
}
//S