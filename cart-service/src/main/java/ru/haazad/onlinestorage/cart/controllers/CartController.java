package ru.haazad.onlinestorage.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.dtos.CartDto;
import ru.haazad.onlinestorage.api.dtos.ProductDto;
import ru.haazad.onlinestorage.api.dtos.StringResponse;
import ru.haazad.onlinestorage.cart.integration.ProductServiceIntegration;
import ru.haazad.onlinestorage.cart.services.CartService;
import ru.haazad.onlinestorage.cart.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader String username, @PathVariable String uuid) {
        cartService.merge(getCurrentCartUuid(username, null), getCurrentCartUuid(null, uuid));
    }

    @GetMapping("/{uuid}")
    public CartDto getCartForCurrentUser(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        Cart cart = cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
        return new CartDto(cart.getItems(), cart.getTotalPrice());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addItem(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        ProductDto productDto = productServiceIntegration.getProductById(productId);
        cartService.addItem(getCurrentCartUuid(username, uuid), productDto);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId, @RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void removeItem(@PathVariable Long productId, @RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.removeItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/clear")
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clear(getCurrentCartUuid(username, uuid));
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
