package br.com.gofood.gofood.client.entities;

import br.com.gofood.gofood.address.collections.AddressCollection;
import br.com.gofood.gofood.favorites.entities.FoodFavoritesCollection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
@Getter
@Setter
public class ClientCollection {

    @Id
    private String id;

    @NotBlank(message = "The CPF field is mandatory")
    @NotNull(message = "The CPF of the client must not be blank")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}",
            message = "The CPF must be in the correct format (123.456.789-09) or unformatted (12345678909).")
    @Size(min = 11, max = 14, message = "The CPF field must contain either 11 (unformatted) or 14 (formatted) characters")
    @Schema(example = "12345678909", requiredMode = Schema.RequiredMode.REQUIRED, description = "CPF of the client")
    @Indexed(unique = true)
    private String cpf;

    @NotBlank(message = "The first name field is mandatory")
    @NotNull(message = "The first name of the client must not be blank")
    @Schema(example = "Gustavo", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client first name")
    private String firstName;

    @NotBlank(message = "The last name field is mandatory")
    @NotNull(message = "The last name of the client must not be blank")
    @Schema(example = "Vieira", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client last name")
    private String lastName;

    @NotBlank(message = "The phone field is mandatory")
    @NotNull(message = "The phone of the client must not be blank")
    @Schema(example = "(19)99999-9999", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client contact telephone number")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}|\\d{10,11}", message = "The phone number must be in the format (19)99999-9999 or 19999999999.")
    private String phone;

    @NotBlank(message = "The email field is mandatory")
    @NotNull(message = "The email of the client must not be blank")
    @Email(message = "The email field must contain a valid email address")
    @Schema(example = "gustavo@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "Client email")
    private String email;

    @NotBlank(message = "The password field is mandatory")
    @NotNull(message = "The password of the client must not be blank")
    @Size(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    @Schema(example = "gusta123", minLength = 8, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Client password")
    private String password;

    @Schema(example = "https://firebasestorage.googleapis.com/v0/b/seu-projeto.appspot.com/o/imagens%2Frestaurante.jpg?alt=media",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "URL of the client image stored on Cloudinary")
    private String image;

    private List<AddressCollection> addresses;

    @Valid
    @NotNull(message = "The address list is mandatory")
    @Builder.Default
    private List<String> addressIds = new ArrayList<>();


    private List<String> restaurantFavoriteIds;

    @DBRef
    private List<FoodFavoritesCollection> foodFavorites;

    public void setCpf(String cpf) {
        this.cpf = cpf != null ? cpf.replaceAll("\\D", "") : null;
    }


    private String resetCode;

    private LocalDateTime resetCodeExpiration;

    @Builder.Default
    private Boolean status = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    private LocalDateTime deletedAt;
}
//S
