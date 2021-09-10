package ru.haazad.onlinestorage.webapp.utils;


import lombok.Data;
import ru.haazad.onlinestorage.webapp.dtos.OrderItemDto;
import ru.haazad.onlinestorage.webapp.models.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<OrderItemDto> items;
    private Float totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public boolean add(Long productId) {
        for (OrderItemDto i: items) {
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void decrement(Long productId) {
        Iterator<OrderItemDto> iterator = items.iterator();
        while (iterator.hasNext()) {
            OrderItemDto i = iterator.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

    public void clear() {
        items.clear();
        totalPrice = 0f;
    }

    private void recalculate() {
        totalPrice = 0f;
        for (OrderItemDto i: items) {
            totalPrice += i.getPrice();
        }
    }
}
