package br.com.gofood.gofood.restaurant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRestaurantRequestDTO {

    @NotBlank(message = "The CNPJ field is mandatory")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14}",
            message = "The CNPJ must be in the correct format (30.330.421/0001-41) or unformatted (30330421000141).")
    private String cnpj;

    @NotBlank(message = "The Password field is mandatory")
    @Size(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    @Schema(example = "magic123", minLength = 8, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant password")
    private String password;
}
//S
