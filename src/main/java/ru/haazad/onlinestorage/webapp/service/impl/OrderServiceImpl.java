package ru.haazad.onlinestorage.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Order;
import ru.haazad.onlinestorage.webapp.model.User;
import ru.haazad.onlinestorage.webapp.repository.OrderRepository;
import ru.haazad.onlinestorage.webapp.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        return orderRepository.getOrdersByUserId(id);
    }

    @Override
    public List<User> getUsersByProductId(Long productId) {
        return orderRepository.getUsersByProductId(productId);
    }
}
