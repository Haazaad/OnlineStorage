package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.webapp.dtos.OrderItemDto;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.models.Order;
import ru.haazad.onlinestorage.webapp.models.OrderItem;
import ru.haazad.onlinestorage.webapp.models.User;
import ru.haazad.onlinestorage.webapp.repositories.OrderItemRepository;
import ru.haazad.onlinestorage.webapp.repositories.OrderRepository;
import ru.haazad.onlinestorage.webapp.services.ProductService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    @Transactional
    public void createOrder(OrderDetailsDto orderDetailsDto, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order();
        Cart cart = cartService.getCartForCurrentUser(principal, null);
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setPrice(cart.getTotalPrice());
        order.setUser(user);
        List<OrderItem> itemList = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setProductPrice(i.getProductPrice());
            orderItem.setQuantity(i.getQuantity());
            orderItem.setProduct(productService.findProductById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Could not find product at checkout. Product ID: " + i.getProductId())));
            itemList.add(orderItem);
        }
        order.setItems(itemList);
        orderRepository.save(order);
        cartService.clearCart(principal, null);
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
