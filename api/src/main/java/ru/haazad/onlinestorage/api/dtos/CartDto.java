package ru.haazad.onlinestorage.api.dtos;

import java.util.List;

public class CartDto {
    private List<OrderItemDto> items;
    private Float totalPrice;

    public CartDto() {
    }

    public CartDto(List<OrderItemDto> items, Float totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
