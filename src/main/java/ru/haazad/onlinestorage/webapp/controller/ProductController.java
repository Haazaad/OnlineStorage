package ru.haazad.onlinestorage.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.haazad.onlinestorage.webapp.service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        model.addAttribute("products", productService.findAllProduct());
        return "products";
    }

    @GetMapping("/products/{id}")
    private String showProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findProductById(id));
        return "product";
    }
}
