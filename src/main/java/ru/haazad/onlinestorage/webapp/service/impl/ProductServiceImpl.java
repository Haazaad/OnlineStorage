package ru.haazad.onlinestorage.webapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;
import ru.haazad.onlinestorage.webapp.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllProduct(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterProductByPrice(Float minPrice, Float maxPrice) {
        if (minPrice == null) {
            return productRepository.findAllByPriceLessThanEqual(maxPrice);
        } else if (maxPrice == null) {
            return productRepository.findAllByPriceGreaterThanEqual(minPrice);
        } else {
            return productRepository.findAllByPriceBetween(minPrice, maxPrice);
        }
    }


}
