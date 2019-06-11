package dev.lucasdeabreu.paymentservice.repository;

import dev.lucasdeabreu.paymentservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
