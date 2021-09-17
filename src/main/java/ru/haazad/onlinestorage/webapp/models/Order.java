package ru.haazad.onlinestorage.webapp.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "total_price")
    private Float price;

    @Column(name = "phone")
    private String phone;

    @Column(name = "delivery_address")
    private String address;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> items;
}
