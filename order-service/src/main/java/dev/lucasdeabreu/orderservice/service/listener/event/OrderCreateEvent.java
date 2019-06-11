package dev.lucasdeabreu.orderservice.service.listener.event;

import dev.lucasdeabreu.orderservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateEvent {

    private final Order order;

}
