package ru.haazad.onlinestorage.webapp.service;

import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAllProduct();

    Product findProductById(Long id);

    void createProduct(Product product);

    void changeCoast(Long id, Float diff);

    void deleteProductById(Long id);
}
