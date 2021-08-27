package ru.haazad.onlinestorage.webapp.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dto.ProductDto;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Product> getProductsInCart() {
        return cartService.getProductsInCart();
    }

    @PostMapping
    public void addProductInCart(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        cartService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        cartService.deleteProductById(id);
    }
}
