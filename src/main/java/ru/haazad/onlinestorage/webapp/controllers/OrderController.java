package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.webapp.services.impl.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
        orderService.addOrder(orderDetailsDto, principal.getName());
    }
}
