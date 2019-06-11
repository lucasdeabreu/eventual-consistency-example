package dev.lucasdeabreu.paymentservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private Long id;

    @NotNull
    private String creditCard;

    private String paymentDate;

    @NotNull
    private BigDecimal value;

    @NotNull
    private Long orderId;

}
