package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.webapp.dtos.OrderDto;
import ru.haazad.onlinestorage.webapp.services.impl.OrderService;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
        orderService.createOrder(orderDetailsDto, principal);
    }

    @GetMapping
    public ResponseEntity<?> getOrdersForCurrentOrders(Principal principal, @RequestParam(required = false) Long productId) {
        if (productId != null) {
            return ResponseEntity.ok(orderService.haveOrderByProductId(principal.getName(), productId));
        }
        return ResponseEntity.ok(orderService.findAllByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList()));
    }

}
