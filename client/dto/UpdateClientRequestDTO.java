package br.com.gofood.gofood.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequestDTO {

    @NotBlank(message = "The CPF field is mandatory")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}",
            message = "The CPF must be in the correct format (123.456.789-09) or unformatted (12345678909).")
    @Schema(example = "123.456.789-09", requiredMode = Schema.RequiredMode.REQUIRED, description = "CPF of the client")
    private String cpf;

    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}|\\d{10,11}",
            message = "The phone number must be in the format (19)99999-9999 or 19999999999.")
    @Schema(example = "(19)99999-9999", description = "Client contact telephone number")
    private String phone;

    @Email(message = "The email field must contain a valid email address")
    @Schema(example = "gustavo@gmail.com", description = "Client email")
    private String email;

    @Schema(description = "Client profile image")
    private MultipartFile image;
}
//S

