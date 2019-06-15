package dev.lucasdeabreu.orderservice.service;

import dev.lucasdeabreu.orderservice.domain.Order;
import dev.lucasdeabreu.orderservice.repository.OrderRepository;
import dev.lucasdeabreu.orderservice.service.listener.event.OrderCreateEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    public OrderServiceImpl(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        publisher.publishEvent(new OrderCreateEvent(order));
        return orderRepository.save(order);
    }
}
