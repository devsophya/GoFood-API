package br.com.gofood.gofood.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteClientResponseDTO {
    private String cpf;
    private Boolean status;
    private String message;
    private LocalDateTime deletedAt = LocalDateTime.now();
}
//S

