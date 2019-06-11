package dev.lucasdeabreu.orderservice.service;

import dev.lucasdeabreu.orderservice.domain.Order;

public interface OrderService {
    Order save(Order order);
}
