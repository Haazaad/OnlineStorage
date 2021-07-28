package ru.haazad.onlinestorage.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.service.ProductService;

@Controller
public class CreateProductController {

    private ProductService productService;

    @Autowired
    public CreateProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/create")
    public String showCreateProductForm() {
        return "create_product";
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestParam Long productId, @RequestParam String productTitle, @RequestParam Float productCoast) {
        Product product = new Product(productId, productTitle, productCoast);
        productService.createProduct(product);
        return "redirect:/products";
    }
}
