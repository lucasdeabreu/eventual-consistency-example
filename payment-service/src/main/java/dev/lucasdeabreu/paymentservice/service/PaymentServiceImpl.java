package dev.lucasdeabreu.paymentservice.service;

import dev.lucasdeabreu.paymentservice.domain.Order;
import dev.lucasdeabreu.paymentservice.domain.Payment;
import dev.lucasdeabreu.paymentservice.exception.IncorrectValueException;
import dev.lucasdeabreu.paymentservice.exception.OrderNotFoundException;
import dev.lucasdeabreu.paymentservice.repository.OrderRepository;
import dev.lucasdeabreu.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment save(Payment payment) {
        Order order = getOrder(payment);

        if (isCorrectValue(payment, order)) {
            throw new IncorrectValueException("Incorrect value of payment");
        }

        return paymentRepository.save(payment);
    }

    private boolean isCorrectValue(Payment payment, Order order) {
        return !order.getValue().equals(payment.getValue());
    }

    private Order getOrder(Payment payment) {
        Optional<Order> orderOptional = orderRepository.findById(payment.getOrderId());
        return orderOptional.orElseThrow(() -> {
            throw new OrderNotFoundException("Order " + payment.getOrderId() + " cannot be found");
        });
    }
}
