package dev.lucasdeabreu.paymentservice.controller.dto.mapper;

import dev.lucasdeabreu.paymentservice.controller.dto.PaymentDTO;
import dev.lucasdeabreu.paymentservice.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PaymentDTOMapper {

    public abstract Payment dtoToPayment(PaymentDTO dto);

    public abstract PaymentDTO paymentToDto(Payment payment);

}
