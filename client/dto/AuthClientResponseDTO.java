package br.com.gofood.gofood.client.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthClientResponseDTO {
        private String access_token;
        private String expires_in;
    }
//S

