package br.com.gofood.gofood.orders.controller;

import br.com.gofood.gofood.orders.dto.StatusUpdateDTO;
import br.com.gofood.gofood.orders.entities.Order;
import br.com.gofood.gofood.orders.usecases.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/order")
@RequiredArgsConstructor
@PreAuthorize("hasRole('RESTAURANT')")
public class RestaurantOrderController {

    private final OrderUseCase orderUseCase;

    @PutMapping("/update/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId,
                                                    @RequestBody StatusUpdateDTO statusUpdate) {
        orderUseCase.updateOrderStatus(orderId, statusUpdate.getStatusUpdate());
        return ResponseEntity.ok("Status successfully updated!");
    }

    @GetMapping("/{restaurantId}/get")
    public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable String restaurantId) {
        List<Order> orders = orderUseCase.getRestaurantOrders(restaurantId);
        return ResponseEntity.ok(orders);
    }
}
