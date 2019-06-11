package dev.lucasdeabreu.orderservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {

    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal unitPrice;

    @Min(1)
    @NotNull
    private Integer quantity;
}
