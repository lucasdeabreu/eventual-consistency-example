package dev.lucasdeabreu.orderservice.repository;

import dev.lucasdeabreu.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
