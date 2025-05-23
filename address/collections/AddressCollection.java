package br.com.gofood.gofood.address.collections;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressCollection {

    @Id
    private String id;

    @NotEmpty(message = "The surname field is mandatory")
    @NotBlank(message = "The surname field is mandatory")
    @Schema(example = "House", requiredMode = Schema.RequiredMode.REQUIRED, description = "Customer address surname")
    private String nickname;

    @NotEmpty(message = "The zipcode field is mandatory")
    @NotBlank(message = "The zipcode field is mandatory")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid zip code format. Expected format: 12345-678")
    @JsonDeserialize(using = StringDeserializer.class)
    @Schema(example = "12345-678", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client zip code")
    private String zipcode;

    @NotBlank(message = "The street field is mandatory")
    @Schema(example = "Euclides Street", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client street")
    private String street;

    @Schema(example = "220", description = "Client street number (can be null if there is no number)")
    private Integer number;

    @NotBlank(message = "The neighborhood field is mandatory")
    @Schema(example = "Center", requiredMode = Schema.RequiredMode.REQUIRED, description = "Neighborhood of the client")
    private String neighborhood;

    @NotBlank(message = "The city field is mandatory")
    @Schema(example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client city")
    private String city;

    @NotBlank(message = "The state field is mandatory")
    @Schema(example = "São Paulo", requiredMode = Schema.RequiredMode.REQUIRED, description = "State of the client")
    private String state;

    @NotBlank(message = "The clientId field is mandatory")
    @Schema(example = "65cdef12c8a9a71234567891", requiredMode = Schema.RequiredMode.REQUIRED, description = "ID of the client associated with this address")
    private String clientId;

}
