package br.com.gofood.gofood.orders.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrder {
    private String idFood;

    private String name;

    private String description;

    private int quantity;

    private double price;
}