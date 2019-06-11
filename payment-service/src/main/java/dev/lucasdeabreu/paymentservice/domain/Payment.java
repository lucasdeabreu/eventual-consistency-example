package dev.lucasdeabreu.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @NotNull
    @Column(nullable = false)
    private String creditCard;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @NotNull
    @Column(nullable = false)
    private Long orderId;

}
