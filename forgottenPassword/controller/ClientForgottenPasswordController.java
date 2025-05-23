package br.com.gofood.gofood.forgottenPassword.controller;

import br.com.gofood.gofood.forgottenPassword.dto.ForgotPasswordRequestDTO;
import br.com.gofood.gofood.forgottenPassword.usecases.ClientForgotPasswordUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientForgottenPasswordController {

    private final ClientForgotPasswordUseCase clientForgotPasswordUseCase;

    public ClientForgottenPasswordController(ClientForgotPasswordUseCase clientForgotPasswordUseCase) {
        this.clientForgotPasswordUseCase = clientForgotPasswordUseCase;
    }

    @PostMapping("/forgottenPassword")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        clientForgotPasswordUseCase.execute(request.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "An email with a password recovery code has been sent.");
        return ResponseEntity.ok(response);
    }
}
