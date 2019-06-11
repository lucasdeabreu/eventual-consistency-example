package dev.lucasdeabreu.orderservice.controller;

import dev.lucasdeabreu.orderservice.controller.dto.OrderDTO;
import dev.lucasdeabreu.orderservice.controller.dto.mapper.OrderDTOMapper;
import dev.lucasdeabreu.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDTOMapper mapper;

    public OrderController(OrderService orderService, OrderDTOMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.orderToDto(orderService.save(mapper.dtoToOrder(orderDTO))));
    }
}
