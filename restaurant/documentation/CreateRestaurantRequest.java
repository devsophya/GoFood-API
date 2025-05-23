/*
package br.com.gofood.gofood.modules.restaurant.documentation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Estrutura de criação de um restaurante")
public class CreateRestaurantRequest {

    @NotBlank
    @Schema(description = "CNPJ do restaurante", example = "82.145.693/0003-29")
    private String cnpj;

    @NotBlank
    @Schema(description = "Nome do restaurante", example = "Casa do Hambúrguer")
    private String name;

    @NotBlank
    @Schema(description = "Nome de usuário do restaurante", example = "casadohamburguer")
    private String username;

    @NotBlank
    @Schema(description = "Telefone de contato", example = "(51)94444-9876")
    private String phone;

    @Email
    @Schema(description = "E-mail de contato", example = "atendimento@casadohamburguer.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha para acesso", example = "burger2025")
    private String password;

    @Schema(description = "Descrição do restaurante", example = "Hambúrgueres artesanais com ingredientes selecionados e sabor inigualável.")
    private String description;

    @Valid
    @NotNull
    @Schema(description = "Endereço do restaurante")
    private Address addressCollection;

    @Schema(description = "Lista de tipos de comida oferecidos")
    private List<FoodType> typesFood;

    // Classe interna para representar Address
    @Getter
    @Setter
    @Schema(description = "Estrutura de endereço")
    public static class Address {
        @NotBlank
        @Schema(description = "CEP do restaurante", example = "90000-789")
        private String zipcode;

        @NotBlank
        @Schema(description = "Rua do restaurante", example = "Avenida do Sabor")
        private String street;

        @NotBlank
        @Schema(description = "Número do restaurante", example = "99")
        private String number;

        @NotBlank
        @Schema(description = "Bairro do restaurante", example = "Centro Histórico")
        private String neighborhood;

        @NotBlank
        @Schema(description = "Cidade do restaurante", example = "Porto Alegre")
        private String city;

        @NotBlank
        @Schema(description = "Estado do restaurante", example = "Rio Grande do Sul")
        private String state;
    }

    // Classe interna para representar FoodType
    @Getter
    @Setter
    @Schema(description = "Estrutura do tipo de comida")
    public static class FoodType {
        @NotBlank
        @Schema(description = "Nome do tipo de comida", example = "Cheeseburger Angus")
        private String name;
    }
}

 */

