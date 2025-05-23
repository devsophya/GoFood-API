package br.com.gofood.gofood.orders.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.gofood.gofood.orders.enums.PaymentMethodEnum;
import br.com.gofood.gofood.orders.enums.StatusOrderEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String id;

    private String idClient;

    private String idRestaurant;

    private String addressId;

    private double totalPrice;

    private List<ItemOrder> itemOrder = new ArrayList<>();

    private PaymentMethodEnum payment;

    private StatusOrderEnum status;

    private String observation;

    private boolean rated;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Rating review;
}