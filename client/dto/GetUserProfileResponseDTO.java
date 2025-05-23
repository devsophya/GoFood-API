package br.com.gofood.gofood.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserProfileResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
}
