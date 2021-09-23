package ru.haazad.onlinestorage.webapp.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.webapp.models.Product;
import ru.haazad.onlinestorage.webapp.services.ProductService;
import ru.haazad.onlinestorage.webapp.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService {
    private static final String CART_PREFIX = "webapp_cart_";

    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCartForCurrentUser(String username) {
        if (!isCartExists(username)) {
            redisTemplate.opsForValue().set(CART_PREFIX + username, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(CART_PREFIX + username);
    }

    private void updateCart(String username, Cart cart) {
        redisTemplate.opsForValue().set(CART_PREFIX + username, cart);
    }

    public void addItem(Long productId, String username) {
        Cart cart = getCartForCurrentUser(username);
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Unable to add a product to the cart because product with ID=%d does not exist.", productId)));
        cart.add(product);
        updateCart(username, cart);
    }

    public void removeItem(Long productId, String username) {
        Cart cart = getCartForCurrentUser(username);
        cart.remove(productId);
        updateCart(username, cart);
    }

    public void decrementItem(Long productId, String username) {
        Cart cart = getCartForCurrentUser(username);
        cart.decrement(productId);
        updateCart(username, cart);
    }

    public void clearCart(String username) {
        Cart cart = getCartForCurrentUser(username);
        cart.clear();
        updateCart(username, cart);
    }

    private boolean isCartExists(String username) {
        return redisTemplate.hasKey(CART_PREFIX + username);
    }

}
