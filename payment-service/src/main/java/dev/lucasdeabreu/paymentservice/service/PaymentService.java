package dev.lucasdeabreu.paymentservice.service;

import dev.lucasdeabreu.paymentservice.domain.Payment;
import org.springframework.stereotype.Service;

public interface PaymentService {
    Payment save(Payment payment);
}
