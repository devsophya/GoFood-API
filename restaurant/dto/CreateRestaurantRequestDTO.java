package br.com.gofood.gofood.restaurant.dto;

import br.com.gofood.gofood.restaurant.entities.AddressCollection;
import br.com.gofood.gofood.restaurant.entities.TypeFoodCollection;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
public class CreateRestaurantRequestDTO {

    @NotBlank(message = "The CNPJ field is mandatory")
    @NotNull(message = "The CNPJ of the restaurant must not be blank")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14}",
            message = "The CNPJ must be in the correct format (30.330.421/0001-41) or unformatted (30330421000141).")
    @Size(min = 18, max = 18, message = "The CNPJ field must contain 18 characters")
    @Schema(example = "30.330.421/0001-41", requiredMode = Schema.RequiredMode.REQUIRED, description = "CNPJ of the restaurant")
    @Indexed(unique = true)
    private String cnpj;

    @NotBlank(message = "The name field is mandatory")
    @NotNull(message = "The name of the restaurant must not be blank")
    @Schema(example = "Mix & Magic", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant name")
    private String name;

    @NotBlank(message = "The username field is mandatory")
    @NotNull(message = "The username of the restaurant must not be blank")
    @Pattern(regexp = "\\S+", message = "The username field must not contain spaces")
    @Schema(example = "mix&magic", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant username")
    private String username;

    @NotBlank(message = "The phone field is mandatory")
    @NotNull(message = "The phone of the restaurant must not be blank")
    @Schema(example = "(19)99999-9999", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant contact telephone number")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}|\\d{10,11}", message = "The phone number must be in the format (19)99999-9999 or 19999999999.")
    private String phone;

    @NotBlank(message = "The email field is mandatory")
    @NotNull(message = "The email of the restaurant must not be blank")
    @Email(message = "The email field must contain a valid email address")
    @Schema(example = "mix.magic@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant email")
    private String email;

    @NotBlank(message = "The password field is mandatory")
    @NotNull(message = "The password of the restaurant must not be blank")
    @Size(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    @Schema(example = "magic123", minLength = 8, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Restaurant password")
    private String password;

    @NotBlank(message = "The description field is mandatory")
    @NotNull(message = "The description of the restaurant must not be blank")
    @Schema(example = "Flavors that delight", requiredMode = Schema.RequiredMode.REQUIRED, description = "Brief description of the restaurant" )
    private String description;

    @NotBlank(message = "The image field is mandatory")
    @NotNull(message = "The image of the restaurant must not be blank")
    @Schema(example = "https://firebasestorage.googleapis.com/v0/b/seu-projeto.appspot.com/o/imagens%2Frestaurante.jpg?alt=media",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "URL of the restaurant image stored on Cloudinary")
    private String image;

    @NotNull(message = "The address field is mandatory")
    @DBRef(lazy = false)
    private AddressCollection addressCollection;

    @NotNull(message = "The typeFood field is mandatory")
    @DBRef(lazy = false)
    @JsonProperty("typesFood")
    @Schema(example = "Italian, Japanese, Mexican", requiredMode = Schema.RequiredMode.REQUIRED, description = "Short description of the types of food." )
    private List<TypeFoodCollection> typeFoodCollections;

    private Boolean status = true;
}
//S

