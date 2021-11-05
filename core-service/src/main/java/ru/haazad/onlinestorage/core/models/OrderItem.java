package ru.haazad.onlinestorage.core.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "product_price")
    private Float productPrice;

    @Column(name = "total_price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "order_order_id")
    private Order order;
}
