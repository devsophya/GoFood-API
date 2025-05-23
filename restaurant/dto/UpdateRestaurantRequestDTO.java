package br.com.gofood.gofood.restaurant.dto;

import br.com.gofood.gofood.restaurant.entities.AddressCollection;
import br.com.gofood.gofood.restaurant.entities.TypeFoodCollection;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestaurantRequestDTO {


    @NotBlank(message = "The CNPJ field is mandatory")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14}",
            message = "The CNPJ must be in the correct format (30.330.421/0001-41) or unformatted (30330421000141).")
    @Schema(example = "30.330.421/0001-41", requiredMode = Schema.RequiredMode.REQUIRED, description = "CNPJ of the restaurant")
    @Indexed(unique = true)
    private String cnpj;

    @Schema(example = "Mix & Magic", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant name")
    private String name;


    @Schema(example = "(19)99999-9999", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant contact telephone number")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}|\\d{10,11}", message = "The phone number must be in the format (19)99999-9999 or 19999999999.")
    private String phone;

    @DBRef(lazy = true)
    private AddressCollection addressCollection;

    @DBRef(lazy = true)
    @JsonProperty("typesFood")
    @Schema(example = "Arabic, Brazilian, Healthy", requiredMode = Schema.RequiredMode.REQUIRED, description = "Short description of the types of food." )
    private List<TypeFoodCollection> typeFoodCollections;
}
//S