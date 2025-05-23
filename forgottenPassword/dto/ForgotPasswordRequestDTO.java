package br.com.gofood.gofood.forgottenPassword.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  ForgotPasswordRequestDTO {
    private String email;
}
