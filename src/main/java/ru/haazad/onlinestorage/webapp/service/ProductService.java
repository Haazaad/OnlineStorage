package ru.haazad.onlinestorage.webapp.service;

import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.dto.ProductDto;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDto> findAllProduct();

    ProductDto findProductById(Long id);

    ProductDto createProduct(ProductDto product);

    List<ProductDto> deleteProductById(Long id);

    List<ProductDto> filterProduct(Float minPrice, Float maxPrice) throws Exception;
}
