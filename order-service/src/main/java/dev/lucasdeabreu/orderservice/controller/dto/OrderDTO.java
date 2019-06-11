package dev.lucasdeabreu.orderservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long id;

    @NotEmpty
    private String address;

    @NotNull
    private String confirmationDate;

    @NotNull
    private String status;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderItemDTO> items;
}
