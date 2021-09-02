package ru.haazad.onlinestorage.webapp.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.models.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAllProduct();

    Page<Product> findAllProduct(int pageIndex, int pageSize);

    Optional<Product> findProductById(Long id);

    Product createProduct(Product product);

    void deleteProductById(Long id);

    void modifyProduct(Product product);

    List<Product> filterProductByPrice(Float minPrice, Float maxPrice);

}
