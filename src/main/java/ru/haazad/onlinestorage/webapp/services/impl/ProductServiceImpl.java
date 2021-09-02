package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.models.Product;
import ru.haazad.onlinestorage.webapp.repositories.ProductRepository;
import ru.haazad.onlinestorage.webapp.services.ProductService;

import java.util.List;
import java.util.Optional;

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
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
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
    public void modifyProduct(Product product) {
        productRepository.save(product);
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
