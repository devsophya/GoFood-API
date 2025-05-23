package br.com.gofood.gofood.orders.dto;

import br.com.gofood.gofood.orders.entities.ItemOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderDTO {
    private String idFood;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public ItemOrder toEntity() {
        return ItemOrder.builder()
                .idFood(this.idFood)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .price(this.price)
                .build();
    }
}