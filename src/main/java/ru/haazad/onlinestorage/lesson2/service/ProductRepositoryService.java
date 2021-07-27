package ru.haazad.onlinestorage.lesson2.service;

import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.lesson2.Product;

import java.util.List;

@Component
public interface ProductRepositoryService {
    List<Product> getAllProducts();

    Product getProduct(Long id);
}
