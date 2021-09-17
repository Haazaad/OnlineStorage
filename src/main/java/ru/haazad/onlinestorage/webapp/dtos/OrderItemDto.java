package ru.haazad.onlinestorage.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.haazad.onlinestorage.webapp.models.Product;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private Float productPrice;
    private Float price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.productPrice = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        price = productPrice * quantity;
    }
}
