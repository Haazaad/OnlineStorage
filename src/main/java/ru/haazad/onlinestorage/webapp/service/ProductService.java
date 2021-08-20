package ru.haazad.onlinestorage.webapp.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Service
public interface ProductService {

    List<Product> findAllProduct();

    Page<Product> findAllProduct(int pageIndex, int pageSize);

    Product findProductById(Long id);

    Product createProduct(Product product);

    void deleteProductById(Long id);

    List<Product> filterProductByPrice(Float minPrice, Float maxPrice);

}
