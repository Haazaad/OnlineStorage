package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.models.Product;
import ru.haazad.onlinestorage.webapp.repositories.CartRepository;
import ru.haazad.onlinestorage.webapp.services.CartService;

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
