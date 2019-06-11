package dev.lucasdeabreu.paymentservice.repository;

import dev.lucasdeabreu.paymentservice.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
