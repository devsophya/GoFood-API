package br.com.gofood.gofood.orders.controller;

import br.com.gofood.gofood.orders.dto.CreateDTO;
import br.com.gofood.gofood.orders.entities.Order;
import br.com.gofood.gofood.orders.entities.Rating;
import br.com.gofood.gofood.orders.usecases.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client/order")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CLIENT')")
public class ClientOrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam("client_id") String clientId,
                                         @RequestParam("restaurantId") String restaurantId,
                                         @RequestBody CreateDTO createDTO) {
        if (clientId == null || clientId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("The clientId cannot be null or empty.");
        }
        Order createdOrder = orderUseCase.createOrder(clientId, restaurantId, createDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{clientId}/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String clientId,
                                              @PathVariable String orderId) {
        return orderUseCase.getOrderById(orderId, clientId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
    }

    @GetMapping("/get/{clientId}")
    public ResponseEntity<List<Order>> getClientOrders(@PathVariable String clientId) {
        return ResponseEntity.ok(orderUseCase.getClientOrders(clientId));
    }

    @PostMapping("/{orderId}/rating/{clientId}")
    public ResponseEntity<Map<String, String>> rateOrder(@PathVariable String orderId,
                                                         @PathVariable String clientId,
                                                         @RequestBody Rating rating) {
        orderUseCase.rateOrder(clientId, orderId, rating);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Assessment successfully registered!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}/{orderId}/rating")
    public ResponseEntity<Rating> getOrderRating(@PathVariable String clientId,
                                                 @PathVariable String orderId) {
        return orderUseCase.getOrderById(orderId, clientId)
                .map(Order::getReview)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}