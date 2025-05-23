package br.com.gofood.gofood.client.controllers;

import br.com.gofood.gofood.client.dto.AuthClientRequestDTO;
import br.com.gofood.gofood.client.dto.AuthClientResponseDTO;
import br.com.gofood.gofood.client.usecases.AuthClientUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class AuthClientController {

    @Autowired
    private AuthClientUseCase authClientUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@RequestBody AuthClientRequestDTO authClientRequestDTO) {
        try {
            var result = this.authClientUseCase.generateToken(authClientRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
