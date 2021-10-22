package ru.haazad.onlinestorage.core.utils;

import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.api.dtos.CommentDto;
import ru.haazad.onlinestorage.api.dtos.OrderDto;
import ru.haazad.onlinestorage.api.dtos.OrderItemDto;
import ru.haazad.onlinestorage.api.dtos.ProductDto;
import ru.haazad.onlinestorage.core.models.Comment;
import ru.haazad.onlinestorage.core.models.Order;
import ru.haazad.onlinestorage.core.models.OrderItem;
import ru.haazad.onlinestorage.core.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public CommentDto commentToDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getProductId(), comment.getDescription(), comment.getCreationDate(), comment.getUser().getUsername());
    }

    public OrderDto orderToDto(Order order) {
        List<OrderItemDto> orderItemDtos = order.getItems().stream().map(this::orderItemToDto).collect(Collectors.toList());
        return new OrderDto(order.getId(), orderItemDtos, order.getAddress(), order.getPhone(), order.getPrice());
    }

    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(), orderItem.getQuantity(), orderItem.getProductPrice(), orderItem.getPrice());
    }

    public OrderItemDto orderItemToDto(Product product) {
        return new OrderItemDto(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
    }

    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
