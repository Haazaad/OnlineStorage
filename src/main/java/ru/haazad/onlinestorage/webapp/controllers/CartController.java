package ru.haazad.onlinestorage.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dtos.StringResponse;
import ru.haazad.onlinestorage.webapp.services.impl.CartService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        cartService.merge(principal, uuid);
    }

    @GetMapping("/{uuid}")
    public Cart getCartForCurrentUser(Principal principal, @PathVariable String uuid) {
        return cartService.getCartForCurrentUser(principal, uuid);
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addItem(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addItem(principal, uuid, productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId, Principal principal, @PathVariable String uuid) {
        cartService.decrementItem(principal, uuid, productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void removeItem(@PathVariable Long productId, Principal principal, @PathVariable String uuid) {
        cartService.removeItem(principal, uuid, productId);
    }
}
