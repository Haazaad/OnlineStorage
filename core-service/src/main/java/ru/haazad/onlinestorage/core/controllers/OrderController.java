package ru.haazad.onlinestorage.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.core.services.impl.OrderService;
import ru.haazad.onlinestorage.core.utils.Converter;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        orderService.createOrder(orderDetailsDto, username);
    }

    @GetMapping
    public ResponseEntity<?> getOrdersForCurrentOrders(@RequestHeader String username, @RequestParam(required = false) Long productId) {
        if (productId != null) {
            return ResponseEntity.ok(orderService.haveOrderByProductId(username, productId));
        }
        return ResponseEntity.ok(orderService.findAllByUsername(username).stream().map(converter::orderToDto).collect(Collectors.toList()));
    }

}
