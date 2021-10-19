package ru.haazad.onlinestorage.webapp.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "product_product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationDate;

    @Column(name = "description")
    private String description;
}
