package ru.haazad.onlinestorage.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.haazad.onlinestorage.webapp.models.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private Float price;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getPrice();
    }
}
