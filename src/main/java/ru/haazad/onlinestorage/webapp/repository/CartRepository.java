package ru.haazad.onlinestorage.webapp.repository;

import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Repository
public interface CartRepository {

    List<Product> getProductsInCart();

    void addProduct(Product product);

    void deleteProductById(Long id);

}
