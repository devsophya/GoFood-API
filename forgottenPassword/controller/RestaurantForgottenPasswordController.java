package br.com.gofood.gofood.forgottenPassword.controller;

import br.com.gofood.gofood.forgottenPassword.dto.ForgotPasswordRequestDTO;
import br.com.gofood.gofood.forgottenPassword.usecases.RestaurantForgotPasswordUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantForgottenPasswordController {

    private final RestaurantForgotPasswordUseCase restaurantForgotPasswordUseCase;

    public RestaurantForgottenPasswordController(RestaurantForgotPasswordUseCase restaurantForgotPasswordUseCase) {
        this.restaurantForgotPasswordUseCase = restaurantForgotPasswordUseCase;
    }

    @PostMapping("/forgottenPassword")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody ForgotPasswordRequestDTO request) {
        restaurantForgotPasswordUseCase.execute(request.getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("message", "An email with a password recovery code has been sent.");
        return ResponseEntity.ok(response);
    }
}

