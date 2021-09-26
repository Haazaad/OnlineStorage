package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.models.Product;
import ru.haazad.onlinestorage.webapp.services.ProductService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CartService {
    private static final String CART_PREFIX = "webapp_cart_";

    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    private String getCartKey(Principal principal, String uuid) {
        if (principal != null) {
            return CART_PREFIX + principal.getName();
        }
        return CART_PREFIX + uuid;
    }

    public Cart getCartForCurrentUser(Principal principal, String uuid) {
        String cartKey = getCartKey(principal, uuid);
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public Cart getCartByKey(String cartKey) {
        if (!redisTemplate.hasKey(CART_PREFIX + cartKey)) {
            redisTemplate.opsForValue().set(CART_PREFIX + cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(CART_PREFIX + cartKey);
    }

    private void updateCart(Principal principal, String uuid, Cart cart) {
        String cartKey = getCartKey(principal, uuid);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    private void updateCartByKey(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(CART_PREFIX + cartKey, cart);
    }

    public void addItem(Principal principal, String uuid, Long productId) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        if (cart.add(productId)) {
            updateCart(principal, uuid, cart);
            return;
        }
        Product product = productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to add a product to the cart because product with ID=%d does not exist.", productId)));
        cart.add(product);
        updateCart(principal, uuid, cart);
    }

    public void removeItem(Principal principal, String uuid, Long productId) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        cart.remove(productId);
        updateCart(principal, uuid, cart);
    }

    public void decrementItem(Principal principal, String uuid, Long productId) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        cart.decrement(productId);
        updateCart(principal, uuid, cart);
    }

    public void clearCart(Principal principal, String uuid) {
        Cart cart = getCartForCurrentUser(principal, uuid);
        cart.clear();
        updateCart(principal, uuid, cart);
    }

    public void merge(Principal principal, String uuid) {
        Cart guestCart = getCartByKey(uuid);
        Cart userCart = getCartByKey(principal.getName());
        userCart.merge(guestCart);
        updateCartByKey(principal.getName(), userCart);
        updateCartByKey(uuid, guestCart);
    }
}
