package br.com.gofood.gofood.restaurant.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "addresses")
@Getter
@Setter
public class AddressCollection {

    @Id
    private String id;

    @NotEmpty(message = "The zipcode field is mandatory")
    @NotBlank(message = "The zipcode field is mandatory")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid zip code format. Expected format: 12345-678")
    @JsonDeserialize(using = StringDeserializer.class)
    @Schema(example = "12345-678", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant's zip code")
    private String zipcode;

    @NotBlank(message = "The street field is mandatory")
    @Schema(example = "Euclides Street", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant street")
    private String street;

    @Schema(example = "220", description = "Restaurant number (can be null if there is no number)")
    private Integer number;

    @NotBlank(message = "The neighborhood field is mandatory")
    @Schema(example = "Center", requiredMode = Schema.RequiredMode.REQUIRED, description = "Neighborhood of the restaurant")
    private String neighborhood;

    @NotBlank(message = "The city field is mandatory")
    @Schema(example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant city")
    private String city;

    @NotBlank(message = "The state field is mandatory")
    @Schema(example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED, description = "State of the restaurant")
    private String state;
}
//S