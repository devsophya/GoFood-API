package br.com.gofood.gofood.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressUpdateRequestDTO {

    @NotBlank(message = "The addressId field is mandatory")
    private String id;

    @NotEmpty(message = "The surname field is mandatory")
    @NotBlank(message = "The surname field is mandatory")
    private String surname;

    @NotEmpty(message = "The zipcode field is mandatory")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid zip code format. Expected format: 12345-678")
    private String zipcode;

    @NotBlank(message = "The street field is mandatory")
    private String street;

    private Integer number;

    @NotBlank(message = "The neighborhood field is mandatory")
    private String neighborhood;

    @NotBlank(message = "The city field is mandatory")
    private String city;

    @NotBlank(message = "The state field is mandatory")
    private String state;

}
//S