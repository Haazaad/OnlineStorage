package ru.haazad.onlinestorage.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dto.ProductDto;
import ru.haazad.onlinestorage.webapp.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> showProductsPage() {
        return productService.findAllProduct();
    }

    @GetMapping("/products/{id}")
    public ProductDto showProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products/delete/{id}")
    public List<ProductDto> deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    // Как изменить status code ответа, в случае отсутсвия обязательных параметров запроса?
    // Также хотелось бы узнать как вывести исключение в ответ на запрос.
    @GetMapping("/products/filter")
    public List<ProductDto> filterProduct(@RequestParam(required = false) Float minPrice, @RequestParam(required = false) Float maxPrice) throws Exception {
        return productService.filterProduct(minPrice, maxPrice);
    }

}
