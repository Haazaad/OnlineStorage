package ru.haazad.onlinestorage.webapp.repository;

import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Component
public interface ProductRepository {

    List<Product> findAllProduct();

    Product findProduct(Long id);

    void addProduct(Product product);
}
