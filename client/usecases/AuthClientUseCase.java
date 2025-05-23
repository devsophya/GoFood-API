package br.com.gofood.gofood.client.usecases;

import br.com.gofood.gofood.client.dto.AuthClientRequestDTO;
import br.com.gofood.gofood.client.dto.AuthClientResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthClientUseCase {
    @Value("${security.token.secret.client}")
    private String secretKey;

    public AuthClientResponseDTO generateToken(AuthClientRequestDTO authClientRequestDTO) {

        if (authClientRequestDTO.getEmail() == null || authClientRequestDTO.getPassword() == null) {
            throw new RuntimeException("Email e senha são obrigatórios!");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires_in = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("Develfood")
                .withSubject(authClientRequestDTO.getEmail())
                .withExpiresAt(expires_in)
                .withClaim("roles", Arrays.asList("CLIENT"))
                .sign(algorithm);

        return AuthClientResponseDTO.builder()
                .access_token(token)
                .expires_in(expires_in.toString())
                .build();
    }
}
