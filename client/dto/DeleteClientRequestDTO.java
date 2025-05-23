package br.com.gofood.gofood.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteClientRequestDTO {

    @NotBlank(message = "The CPF field is mandatory")
    @NotNull(message = "The CPF of the client must not be blank")
    @Pattern(
            regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}",
            message = "The CPF must be in the correct format (123.456.789-09) or unformatted (12345678909)."
    )
    @Schema(example = "123.456.789-09", requiredMode = Schema.RequiredMode.REQUIRED, description = "CPF of the client")
    @Indexed(unique = true)
    private String cpf;

    @NotBlank(message = "The Password field is mandatory")
    @Size(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    @Schema(example = "magic123", minLength = 8, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant password")
    private String password;
}
//S

