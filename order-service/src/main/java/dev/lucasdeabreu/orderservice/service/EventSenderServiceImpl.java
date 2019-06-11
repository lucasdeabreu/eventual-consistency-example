package dev.lucasdeabreu.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventSenderServiceImpl implements EventSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public EventSenderServiceImpl(final RabbitTemplate rabbitTemplate, final ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void send(String queueName, Object payload) {
        rabbitTemplate.convertAndSend(queueName, convertToJson(payload));
    }

    private String convertToJson(final Object payload) {
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert " + payload + " to json", e);
        }
    }
}
