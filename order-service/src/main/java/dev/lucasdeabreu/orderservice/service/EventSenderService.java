package dev.lucasdeabreu.orderservice.service;

public interface EventSenderService {
    void send(String queueName, Object payload);
}
