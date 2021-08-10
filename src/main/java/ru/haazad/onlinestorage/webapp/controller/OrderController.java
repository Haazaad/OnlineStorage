package ru.haazad.onlinestorage.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.haazad.onlinestorage.webapp.service.OrderService;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @ResponseBody
    public String showOrders(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long productId) {
        if (userId == null) {
            return orderService.getAllOrders().toString();
        }
        if (productId != null) {
            return orderService.getOrdersByUserId(productId).toString();
        }
        return orderService.getOrdersByUserId(userId).toString();
    }
}
