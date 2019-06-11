package dev.lucasdeabreu.paymentservice.controller;

import dev.lucasdeabreu.paymentservice.controller.dto.PaymentDTO;
import dev.lucasdeabreu.paymentservice.controller.dto.mapper.PaymentDTOMapper;
import dev.lucasdeabreu.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDTOMapper mapper;

    public PaymentController(PaymentService paymentService, PaymentDTOMapper mapper) {
        this.paymentService = paymentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity payOrder(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.paymentToDto(paymentService.save(mapper.dtoToPayment(paymentDTO))));
    }

}
