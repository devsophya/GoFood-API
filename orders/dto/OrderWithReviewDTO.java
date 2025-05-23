package br.com.gofood.gofood.orders.dto;

import br.com.gofood.gofood.orders.entities.Order;
import br.com.gofood.gofood.orders.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithReviewDTO {
    private Order order;
    private Rating review;
}