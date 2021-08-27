package ru.haazad.onlinestorage.webapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.CartRepository;
import ru.haazad.onlinestorage.webapp.service.CartService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public List<Product> getProductsInCart() {
        return cartRepository.getProductsInCart();
    }

    @Override
    public void addProduct(Product product) {
        cartRepository.addProduct(product);
    }

    @Override
    public void deleteProductById(Long id) {
        cartRepository.deleteProductById(id);
    }
}
