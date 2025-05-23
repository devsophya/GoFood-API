package br.com.gofood.gofood.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddressDeleteRequestDTO {

    @NotBlank(message = "The clientId field is mandatory")
    private String clientId;

    @NotBlank(message = "The addressId field is mandatory")
    private String addressId;
}
//S
