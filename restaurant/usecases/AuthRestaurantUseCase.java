package br.com.gofood.gofood.restaurant.usecases;

import br.com.gofood.gofood.restaurant.dto.AuthRestaurantResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthRestaurantUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthRestaurantResponseDTO generateToken(AuthRestaurantResponseDTO authRestaurantResponseDTO) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires_in = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("Develfood")
                .withSubject("anonymous")
                .withExpiresAt(expires_in)
                .withClaim("roles", Arrays.asList("RESTAURANT"))
                .sign(algorithm);

        return AuthRestaurantResponseDTO.builder()
                .access_token(token)
                .expires_in(expires_in.toString())
                .build();
    }
}
//S
