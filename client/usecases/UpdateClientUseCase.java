package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.client.dto.UpdateClientResponseDTO;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.repositories.ClientRepository;
import br.com.gofood.gofood.exceptions.ClientNotFoundCPFException;
import br.com.gofood.gofood.exceptions.ClientDeletedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;
    private final CloudinaryClientUseCase cloudinaryUseCase;

    public ClientCollection updateClientByCpf(String cpf, String phone, String email, MultipartFile image) throws IOException {
        ClientCollection client = clientRepository.findByCpf(cpf)
                .orElseThrow(ClientNotFoundCPFException::new);

        if (!client.getStatus()) {
            throw new ClientDeletedException();
        }

        if (phone != null && !phone.isEmpty()) {
            client.setPhone(phone);
        }
        if (email != null && !email.isEmpty()) {
            client.setEmail(email);
        }
        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryUseCase.uploadImage(image);
            client.setImage(imageUrl);
        }

        return clientRepository.save(client);
    }
}
//S
