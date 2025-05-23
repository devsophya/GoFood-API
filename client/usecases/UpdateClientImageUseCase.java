package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.client.dto.UpdateClientImageResponseDTO;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.ClientNotFoundCPFException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class UpdateClientImageUseCase {

    private final CloudinaryClientUseCase cloudinaryClientUseCase;
    private final ClientRepository clientRepository;

    public UpdateClientImageResponseDTO execute(String clientId, MultipartFile imageFile) throws IOException {
        ClientCollection client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundCPFException::new);

        String imageUrl = cloudinaryClientUseCase.uploadImage(imageFile);
        client.setImage(imageUrl);
        clientRepository.save(client);

        return new UpdateClientImageResponseDTO(imageUrl);
    }
}
//S
