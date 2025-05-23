package br.com.gofood.gofood.orders.usecases;

import br.com.gofood.gofood.orders.dto.OrderStatusUpdateDTO;
import br.com.gofood.gofood.orders.entities.Order;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderStatusNotificationUseCase {
    private final SimpMessagingTemplate messagingTemplate;


    public OrderStatusNotificationUseCase(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyStatusChange(Order order) {
        OrderStatusUpdateDTO dto = new OrderStatusUpdateDTO(
                order.getId().toString(),
                order.getStatus().name()
        );

        messagingTemplate.convertAndSend("/topic/order-status", dto);
    }
}

