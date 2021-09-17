package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.services.impl.CartService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCartForCurrentUser() {
        return cartService.getCartForCurrentUser();
    }

    @GetMapping("/add/{productId}")
    public void addItem(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }
}
