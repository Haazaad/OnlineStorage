package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.webapp.models.Order;
import ru.haazad.onlinestorage.webapp.models.OrderItem;
import ru.haazad.onlinestorage.webapp.models.User;
import ru.haazad.onlinestorage.webapp.repositories.OrderItemRepository;
import ru.haazad.onlinestorage.webapp.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    @Transactional
    public void addOrder(OrderDetailsDto orderDetailsDto, String username) {
        User user = userService.findByUsername(username);
        Order order = new Order();
        Collection<OrderItem> orderItems = cartService.getCartForCurrentUser().getItems().stream().map(OrderItem::new).collect(Collectors.toList());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setPrice(cartService.getCartForCurrentUser().getTotalPrice());
        order.setUser(user);
        order.setItems(orderItems);
        orderRepository.save(order);
        order.getItems().forEach(i -> i.setOrder(order));
        orderItemRepository.saveAll(order.getItems());
        cartService.clearCart();
    }
}
