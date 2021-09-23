package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.services.impl.CartService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCartForCurrentUser(Principal principal) {
        return cartService.getCartForCurrentUser(principal.getName());
    }

    @GetMapping("/add/{productId}")
    public void addItem(@PathVariable Long productId, Principal principal) {
        cartService.addItem(productId, principal.getName());
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId, Principal principal) {
        cartService.decrementItem(productId, principal.getName());
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId, Principal principal) {
        cartService.removeItem(productId, principal.getName());
    }
}
