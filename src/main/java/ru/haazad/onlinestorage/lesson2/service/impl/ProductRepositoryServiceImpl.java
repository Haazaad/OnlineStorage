package ru.haazad.onlinestorage.lesson2.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.lesson2.Product;
import ru.haazad.onlinestorage.lesson2.service.ProductRepositoryService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class ProductRepositoryServiceImpl implements ProductRepositoryService {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productList.add(new Product((long) i + 1, "Product", (float) Math.random() * 100));
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProduct(Long id) {
        return productList.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }
}
