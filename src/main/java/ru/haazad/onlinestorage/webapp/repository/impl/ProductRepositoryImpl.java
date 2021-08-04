package ru.haazad.onlinestorage.webapp.repository.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "First", 1.1f),
                new Product(2L, "Second", 22.2f)
                ));
    }

    @Override
    public List<Product> findAllProduct() {
        return Collections.unmodifiableList(productList);
    }

    @Override
    public Product findProduct(Long id) {
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void changeCoast(Long id, Float diff) {
        Product product = findProduct(id);
        product.setCoast(product.getCoast() + diff);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = findProduct(id);
        productList.remove(product);
    }
}
