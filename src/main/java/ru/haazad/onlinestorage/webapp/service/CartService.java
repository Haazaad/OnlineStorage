package ru.haazad.onlinestorage.webapp.service;

import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Service
public interface CartService {

    List<Product> getProductsInCart();

    void addProduct(Product product);

    void deleteProductById(Long id);
}
