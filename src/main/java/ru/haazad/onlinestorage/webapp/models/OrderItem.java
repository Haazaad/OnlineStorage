package ru.haazad.onlinestorage.webapp.models;

import lombok.Data;
import ru.haazad.onlinestorage.webapp.dtos.OrderItemDto;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "product_product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "total_price")
    private Float price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_order_id")
    private Order order;

    public OrderItem(OrderItemDto orderItemDto) {
        this.productId = orderItemDto.getProductId();
        this.quantity = orderItemDto.getQuantity();
        this.productPrice = orderItemDto.getProductPrice();
        this.price = orderItemDto.getPrice();
    }

    public OrderItem() {

    }
}
