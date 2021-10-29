package ru.haazad.onlinestorage.core.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.api.dtos.CartDto;
import ru.haazad.onlinestorage.api.dtos.OrderDetailsDto;
import ru.haazad.onlinestorage.api.dtos.OrderItemDto;
import ru.haazad.onlinestorage.api.dtos.UserDto;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.core.integration.CartServiceIntegration;
import ru.haazad.onlinestorage.core.integration.UserServiceIntegration;
import ru.haazad.onlinestorage.core.models.Order;
import ru.haazad.onlinestorage.core.models.OrderItem;
import ru.haazad.onlinestorage.core.repositories.OrderRepository;
import ru.haazad.onlinestorage.core.services.ProductService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final UserServiceIntegration userServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public void createOrder(OrderDetailsDto orderDetailsDto, String username) {
        Order order = new Order();
        CartDto cartDto = cartServiceIntegration.getUserCartDto(username);
        UserDto userDto = userServiceIntegration.getUserDto(username);
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setPrice(cartDto.getTotalPrice());
        order.setUserId(userDto.getId());
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
        cartServiceIntegration.clearUserCart(username);
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public boolean haveOrderByProductId(String username, Long productId) {
        UserDto userDto = userServiceIntegration.getUserDto(username);
        return orderRepository.hasOrder(userDto.getId(), productId);
    }
}
