package dev.lucasdeabreu.orderservice.service;

import dev.lucasdeabreu.orderservice.domain.Order;
import dev.lucasdeabreu.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.lucasdeabreu.orderservice.util.TransactionalUtil.afterCommit;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    private final EventSenderService eventSenderService;
    private final String orderQueueName;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(final EventSenderService eventSenderService,
                            @Value("${queue.name.order}") final String orderQueueName,
                            final OrderRepository orderRepository) {
        this.eventSenderService = eventSenderService;
        this.orderQueueName = orderQueueName;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        afterCommit(() -> {
            eventSenderService.send(orderQueueName, order);
        });
        return orderRepository.save(order);
    }
}
