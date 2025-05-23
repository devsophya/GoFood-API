package br.com.gofood.gofood.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDTO {

    private String id;

    @NotBlank(message = "idClient não pode ser vazio")
    private String idClient;

    @NotBlank(message = "idRestaurant não pode ser vazio")
    private String idRestaurant;

    @NotBlank(message = "addressId não pode ser vazio")
    private String addressId;

    @Positive(message = "totalPrice deve ser positivo")
    private double totalPrice;

    @NotNull(message = "itemOrder não pode ser vazio")
    private List<ItemOrderDTO> itemOrder;

    @NotBlank(message = "payment não pode ser vazio")
    private String payment;

    private List<ItemOrderDTO> items;

    @NotBlank(message = "status não pode ser vazio")
    private String status;

    private String observation;

    private boolean rated;

    private String createdAt;

    private String updatedAt;

    private String review;
}
