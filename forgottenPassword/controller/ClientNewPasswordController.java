package br.com.gofood.gofood.forgottenPassword.controller;

import br.com.gofood.gofood.forgottenPassword.dto.NewPasswordRequestDTO;
import br.com.gofood.gofood.forgottenPassword.usecases.ClientNewPasswordUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientNewPasswordController {

    private final ClientNewPasswordUseCase clientNewPasswordUseCase;

    public ClientNewPasswordController(ClientNewPasswordUseCase clientNewPasswordUseCase) {
        this.clientNewPasswordUseCase = clientNewPasswordUseCase;
    }

    @PutMapping("/newPassword")
    public ResponseEntity<Map<String, String>> newPassword(@RequestBody NewPasswordRequestDTO request) {
        clientNewPasswordUseCase.execute(request.getEmail(), request.getCode(), request.getNewPassword());
        Map<String, String> response = new HashMap<>();
        response.put("message", "New password successfully registered!");
        return ResponseEntity.ok(response);
    }
}
