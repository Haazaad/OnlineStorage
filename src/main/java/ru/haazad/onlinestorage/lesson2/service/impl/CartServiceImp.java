package ru.haazad.onlinestorage.lesson2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.lesson2.Product;
import ru.haazad.onlinestorage.lesson2.service.CartService;
import ru.haazad.onlinestorage.lesson2.service.ProductRepositoryService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceImp implements CartService {
    private final ProductRepositoryService productRepository;

    private final List<Product> productList;

    @Autowired
    public CartServiceImp(ProductRepositoryService productRepository) {
        this.productRepository = productRepository;
        productList = new ArrayList<>();
    }

    @Override
    public void addProduct(Long id) {
        productList.add(productRepository.getProduct(id));
    }

    @Override
    public boolean removeProduct(Long id) {
        Product product = productRepository.getProduct(id);
        return productList.remove(product);
    }

    @Override
    public boolean isEmpty() {
        return productList.isEmpty();
    }

    @Override
    public String toString() {
        if (!productList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Product p: productList) {
                sb.append(p).append(System.lineSeparator());
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
        return "Cart is empty";
    }
}
