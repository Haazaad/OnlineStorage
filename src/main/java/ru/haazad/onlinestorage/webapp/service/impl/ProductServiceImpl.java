package ru.haazad.onlinestorage.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;
import ru.haazad.onlinestorage.webapp.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProduct(id);
    }

    @Override
    public void createProduct(Product product) {
        productRepository.addProduct(product);
    }
}
