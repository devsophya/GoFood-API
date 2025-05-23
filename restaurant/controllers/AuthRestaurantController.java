package br.com.gofood.gofood.restaurant.controllers;

import br.com.gofood.gofood.restaurant.dto.AuthRestaurantResponseDTO;
import br.com.gofood.gofood.restaurant.usecases.AuthRestaurantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurant")
public class AuthRestaurantController {

    private final AuthRestaurantUseCase authRestaurantUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthRestaurantResponseDTO authRestaurantResponseDTO) {
        try{
            var result = this.authRestaurantUseCase.generateToken(authRestaurantResponseDTO);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }

    }
}
//S
