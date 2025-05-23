package br.com.gofood.gofood.client.controllers;

import br.com.gofood.gofood.address.usecases.CreateAddressUseCase;
import br.com.gofood.gofood.client.dto.*;
import br.com.gofood.gofood.client.entities.ClientCollection;
import br.com.gofood.gofood.client.usecases.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateClientImageUseCase updateClientImageUseCase;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody @Valid ClientCollection clientCollection) {
        var result = this.createClientUseCase.execute(clientCollection);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping(value = "/update/image/{clientId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UpdateClientImageResponseDTO> uploadImage(
            @PathVariable String clientId,
            @RequestParam("image") MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The image file is mandatory.");
        }
        UpdateClientImageResponseDTO responseDTO = updateClientImageUseCase.execute(clientId, imageFile);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteClientResponseDTO> deleteClient(@RequestBody DeleteClientRequestDTO request) {
        DeleteClientResponseDTO response = deleteClientUseCase.deleteClient(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UpdateClientResponseDTO> updateClient(
            @RequestParam String cpf,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestPart(required = false) MultipartFile imageFile
    ) throws IOException {
        ClientCollection updatedClient = updateClientUseCase.updateClientByCpf(cpf, phone, email, imageFile);
        UpdateClientResponseDTO responseDTO = new UpdateClientResponseDTO(
                updatedClient.getPhone(),
                updatedClient.getEmail()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/profile/{cpf}")
    public ResponseEntity<GetUserProfileResponseDTO> getProfile(@PathVariable String cpf) {
        var response = getUserProfileUseCase.getUserProfile(cpf);
        return ResponseEntity.ok().body(response);
    }
}

