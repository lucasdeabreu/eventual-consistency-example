package dev.lucasdeabreu.orderservice.controller.dto.mapper;

import dev.lucasdeabreu.orderservice.controller.dto.OrderDTO;
import dev.lucasdeabreu.orderservice.domain.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class OrderDTOMapper {

    @AfterMapping
    protected void fillOrderOnItems(@MappingTarget Order order) {
        if (order.getItems() != null) {
            order.getItems().forEach(i -> i.setOrder(order));
        }
    }

    public abstract Order dtoToOrder(OrderDTO dto);

    public abstract OrderDTO orderToDto(Order order);
}
