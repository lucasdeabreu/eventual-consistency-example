package dev.lucasdeabreu.orderservice.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lucasdeabreu.orderservice.service.listener.event.OrderCreateEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Component
public class OrderEventListener {

    private static final String ORDER_CREATE = "ORDER_CREATE";

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    private final String orderTopicName;

    public OrderEventListener(RabbitTemplate rabbitTemplate,
                              ObjectMapper mapper,
                              @Value("${order.topic.name}") String orderTopicName) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
        this.orderTopicName = orderTopicName;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCreateEvent(OrderCreateEvent createEvent) {
        log.info("Sending order create event to {}, order id: {}", orderTopicName, createEvent.getOrder().getId());
        rabbitTemplate.convertAndSend(orderTopicName, ORDER_CREATE, convertToJson(createEvent.getOrder()));
    }

    private String convertToJson(final Object payload) {
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert " + payload + " to json", e);
        }
    }
}

