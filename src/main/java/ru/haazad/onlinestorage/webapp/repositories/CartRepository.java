package ru.haazad.onlinestorage.webapp.repositories;

import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.models.Product;

import java.util.List;

@Repository
public interface CartRepository {

    List<Product> getProductsInCart();

    void addProduct(Product product);

    void deleteProductById(Long id);

}
