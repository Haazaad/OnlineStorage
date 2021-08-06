package ru.haazad.onlinestorage.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "coast")
    private Float coast;

    public Product() {
    }

    public Product(String title, Float coast) {
        this.title = title;
        this.coast = coast;
    }

    public Product(Long id, String title, Float coast) {
        this.id = id;
        this.title = title;
        this.coast = coast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getCoast() {
        return coast;
    }

    public void setCoast(Float cost) {
        this.coast = cost;
    }
}
