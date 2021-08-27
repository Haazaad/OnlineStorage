package ru.haazad.onlinestorage.webapp.repository.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.CartRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class CartRepositoryImpl implements CartRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
    }

    @Override
    public List<Product> getProductsInCart() {
        return productList;
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productList.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        productList.remove(product);
    }
}
