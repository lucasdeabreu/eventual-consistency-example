package dev.lucasdeabreu.paymentservice.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lucasdeabreu.paymentservice.domain.Order;
import dev.lucasdeabreu.paymentservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class OrderHandler {

    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;

    public OrderHandler(OrderRepository orderRepository, ObjectMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @RabbitListener(queues = {"${queue.name.order.create}"})
    public void receive(@Payload String payload) {
        Order order = convert(payload);
        order.calculateValue();
        log.info("Order saved {}", orderRepository.save(order));
    }

    private Order convert(String payload) {
        TypeReference<Order> typeRef = new TypeReference<>() {
        };
        try {
            return mapper.readValue(payload, typeRef);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert payload to Map", e);
        }
    }

}
