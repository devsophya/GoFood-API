package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.client.dto.DeleteClientRequestDTO;
import br.com.gofood.gofood.client.dto.DeleteClientResponseDTO;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.ClientNotFoundCPFException;
import br.com.gofood.gofood.exceptions.ClientWrongPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DeleteClientUseCase {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DeleteClientResponseDTO deleteClient(DeleteClientRequestDTO request) {
        ClientCollection client = clientRepository.findByCpf(request.getCpf())
                .orElseThrow(ClientNotFoundCPFException::new);

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw new ClientWrongPasswordException();
        }

        client.setStatus(false);
        client.setDeletedAt(LocalDateTime.now());
        clientRepository.save(client);

        return new DeleteClientResponseDTO(client.getCpf(), client.getStatus(), "Client successfully deleted", client.getDeletedAt());
    }
}
//S