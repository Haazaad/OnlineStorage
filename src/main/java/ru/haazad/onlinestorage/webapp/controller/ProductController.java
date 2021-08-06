package ru.haazad.onlinestorage.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.haazad.onlinestorage.webapp.service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProductsPage(Model model, @RequestParam(required = false) Long productId) {
        if (productId == null) {
            model.addAttribute("products", productService.findAllProduct());
            return "products";
        }
        return showProduct(model, productId);
    }

    @PostMapping("/products")
    public String changeProductPrice(@RequestParam Long productId, @RequestParam Float difference) {
        productService.changePrice(productId, difference);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    private String showProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findProductById(id));
        return "product";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/products";
    }
}
