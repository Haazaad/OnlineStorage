package ru.haazad.onlinestorage.lesson2;

public class Product {
    private final Long id;
    private final String title;
    private final float price;

    public Product(Long id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price;
    }
}
