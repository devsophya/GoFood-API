package br.com.gofood.gofood.orders.usecases;

import br.com.gofood.gofood.exceptions.OrderAlreadyRatedException;
import br.com.gofood.gofood.exceptions.OrderNotDeliveredException;
import br.com.gofood.gofood.orders.dto.CreateDTO;
import br.com.gofood.gofood.orders.dto.ItemOrderDTO;
import br.com.gofood.gofood.orders.entities.Order;
import br.com.gofood.gofood.orders.entities.Rating;
import br.com.gofood.gofood.orders.enums.PaymentMethodEnum;
import br.com.gofood.gofood.orders.enums.StatusOrderEnum;
import br.com.gofood.gofood.orders.repositories.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderUseCase {

    private final OrderRepository orderRepository;
    private final WebSocketNotificationUseCase webSocketNotificationUseCase;
    private final OrderStatusNotificationUseCase orderStatusNotificationUseCase;

    public OrderUseCase(OrderRepository orderRepository, WebSocketNotificationUseCase webSocketNotificationUseCase,OrderStatusNotificationUseCase orderStatusNotificationUseCase) {
        this.orderRepository = orderRepository;
        this.webSocketNotificationUseCase = webSocketNotificationUseCase;
        this.orderStatusNotificationUseCase = orderStatusNotificationUseCase;
    }

    public Order createOrder(String clientId, String restaurantId, CreateDTO createDTO) {
        validateMandatoryFields(clientId, restaurantId);

        if (createDTO.getItems() == null || createDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um item.");
        }

        PaymentMethodEnum paymentMethod;
        try {
            paymentMethod = PaymentMethodEnum.valueOf(createDTO.getPayment().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid payment method: " + createDTO.getPayment());
        }

        Order newOrder = Order.builder()
                .idClient(clientId)
                .idRestaurant(restaurantId)
                .addressId(createDTO.getAddressId())
                .payment(paymentMethod)
                .observation(createDTO.getObservation())
                .itemOrder(createDTO.getItems().stream().map(ItemOrderDTO::toEntity).toList())
                .totalPrice(createDTO.getItems().stream()
                        .mapToDouble(i -> i.getPrice() * i.getQuantity())
                        .sum())
                .status(StatusOrderEnum.WAITING_FOR_APPROVAL)
                .build();


        return orderRepository.save(newOrder);
    }

    public Optional<Order> getOrderById(String orderId, String clientId) {
        return orderRepository.findById(new ObjectId(orderId))
                .filter(order -> order.getIdClient().equals(clientId))
                .map(order -> {
                    order.setReview(order.getReview());
                    return order;
                });
    }

    public List<Order> getClientOrders(String clientId) {
        return orderRepository.findByIdClientOrderByCreatedAtDesc(clientId);
    }

    public void rateOrder(String clientId, String orderId, Rating rating) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (!order.getIdClient().equals(clientId)) {
            throw new IllegalArgumentException("This order does not belong to the client.");
        }

        if (order.isRated()) {
            throw new OrderAlreadyRatedException("This order has already been rated.");
        }

        if (!StatusOrderEnum.DELIVERED.equals(order.getStatus())) {
            throw new OrderNotDeliveredException("Cannot rate an order that is not delivered.");
        }

        if (rating.getCreatedAt() == null) {
            rating.setCreatedAt(LocalDateTime.now());
        }
        order.setReview(rating);
        order.setRated(true);
        orderRepository.save(order);
    }


    public List<Rating> getRestaurantRatings(String restaurantId) {
        List<Rating> ratings = orderRepository.findByIdRestaurantOrderByCreatedAtDesc(restaurantId)
                .stream()
                .map(Order::getReview)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ratings.isEmpty() ? Collections.emptyList() : ratings;
    }

    public Optional<Rating> getOrderRating(String orderId, String clientId) {
        return orderRepository.findById(new ObjectId(orderId))
                .filter(order -> order.getIdClient().equals(clientId))
                .map(Order::getReview);
    }

    private void validateMandatoryFields(String clientId, String restaurantId) {
        if (clientId == null || clientId.trim().isEmpty()) {
            throw new IllegalArgumentException("The clientId cannot be null or empty.");
        }
        if (restaurantId == null || restaurantId.trim().isEmpty()) {
            throw new IllegalArgumentException("The restaurantId cannot be null or empty.");
        }
    }

    public void updateOrderStatus(String orderId, StatusOrderEnum newStatus) {
        Order order = orderRepository.findById(new ObjectId(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        orderStatusNotificationUseCase.notifyStatusChange(order);
    }

    public List<Order> getRestaurantOrders(String restaurantId) {
        return orderRepository.findByIdRestaurantOrderByCreatedAtDesc(restaurantId);
    }
}