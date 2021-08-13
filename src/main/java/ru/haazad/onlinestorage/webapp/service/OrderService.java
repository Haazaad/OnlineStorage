package ru.haazad.onlinestorage.webapp.service;

import ru.haazad.onlinestorage.webapp.model.Order;
import ru.haazad.onlinestorage.webapp.model.User;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long id);

    List<User> getUsersByProductId(Long productId);
}
