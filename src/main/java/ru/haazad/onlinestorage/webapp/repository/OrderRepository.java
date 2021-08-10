package ru.haazad.onlinestorage.webapp.repository;

import ru.haazad.onlinestorage.webapp.model.Order;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.model.User;

import java.util.List;

public interface OrderRepository {

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long id);

    List<User> getUsersByProductId(Long productId);
}
