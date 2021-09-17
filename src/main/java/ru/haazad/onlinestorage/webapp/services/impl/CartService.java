package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.models.Product;
import ru.haazad.onlinestorage.webapp.repositories.CartRepository;
import ru.haazad.onlinestorage.webapp.services.ProductService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;

    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCartForCurrentUser() {
        return cart;
    }

    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to add a product to the cart because product with ID=%d does not exist.", productId)));
        cart.add(product);
    }

    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    public void decrementItem(Long productId) {
        cart.decrement(productId);
    }

    public void clearCart() {
        cart.clear();
    }
}
