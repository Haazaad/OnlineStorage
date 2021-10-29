package ru.haazad.onlinestorage.cart.utils;


import lombok.Data;
import ru.haazad.onlinestorage.api.dtos.OrderItemDto;
import ru.haazad.onlinestorage.api.dtos.ProductDto;

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

    public void add(ProductDto productDto) {
        for (OrderItemDto i: items) {
            if (i.getProductId().equals(productDto.getId())) {
                i.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new OrderItemDto(productDto));
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
        recalculate();
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

    public void merge(Cart another) {
        for (OrderItemDto anotherItem: another.items) {
            boolean merged = false;
            for (OrderItemDto currentItem: items) {
                if (currentItem.getProductId().equals(anotherItem.getProductId())) {
                    currentItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
