    package br.com.gofood.gofood.orders.usecases;

    import lombok.RequiredArgsConstructor;
    import org.springframework.messaging.simp.SimpMessagingTemplate;
    import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class WebSocketNotificationUseCase {

        private final SimpMessagingTemplate messagingTemplate;

        public void notifyClient(String orderId, String clientId, String newStatus) {
            String destination = "/topic/order-status/" + orderId + "/client/" + clientId;
            messagingTemplate.convertAndSend(destination, newStatus);
        }

        public void notifyRestaurant(String orderId, String restaurantId, String newStatus) {
            String destination = "/topic/order-status/" + orderId + "/restaurant/" + restaurantId;
            messagingTemplate.convertAndSend(destination, newStatus);
        }
    }
