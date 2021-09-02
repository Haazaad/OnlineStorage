package ru.haazad.onlinestorage.webapp.services;

import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.models.Product;

import java.util.List;

@Service
public interface CartService {

    List<Product> getProductsInCart();

    void addProduct(Product product);

    void deleteProductById(Long id);
}
