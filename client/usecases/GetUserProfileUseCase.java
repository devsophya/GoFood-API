package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.client.dto.GetUserProfileResponseDTO;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class GetUserProfileUseCase {

    private final ClientRepository clientRepository;

    public GetUserProfileResponseDTO getUserProfile(String cpf) {
        ClientCollection client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!client.getStatus()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is deactivated");
        }

        return new GetUserProfileResponseDTO(
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                client.getImage()
        );
    }
}

