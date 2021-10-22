package ru.haazad.onlinestorage.core.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.api.dtos.CartDto;
import ru.haazad.onlinestorage.api.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.api.dtos.OrderItemDto;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.core.integration.CartServiceIntegration;
import ru.haazad.onlinestorage.core.models.Order;
import ru.haazad.onlinestorage.core.models.OrderItem;
import ru.haazad.onlinestorage.core.models.User;
import ru.haazad.onlinestorage.core.repositories.OrderRepository;
import ru.haazad.onlinestorage.core.services.ProductService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;

    @Transactional
    public void createOrder(OrderDetailsDto orderDetailsDto, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order();
        CartDto cartDto = cartServiceIntegration.getUserCartDto(principal);
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setPrice(cartDto.getTotalPrice());
        order.setUser(user);
        List<OrderItem> itemList = new ArrayList<>();
        for (OrderItemDto i : cartDto.getItems()) {
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
        cartServiceIntegration.clear(principal);
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public boolean haveOrderByProductId(String username, Long productId) {
        return orderRepository.hasOrder(userService.findByUsername(username).getId(), productId);
    }
}
