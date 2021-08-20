package ru.haazad.onlinestorage.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.webapp.dto.ProductDto;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private static final int PAGE_SIZE = 10;

    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDto> showAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAllProduct(pageIndex - 1, PAGE_SIZE).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    public ProductDto showProduct(@PathVariable Long id) {
        return new ProductDto(productService.findProductById(id));
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return new ProductDto(productService.createProduct(product));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/products/filter")
    public List<ProductDto> filterProductByPrice(@RequestParam(required = false) Float minPrice, @RequestParam(required = false) Float maxPrice) {
        if (minPrice != null || maxPrice != null) {
            return productService.filterProductByPrice(minPrice, maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
        }
        return productService.findAllProduct().stream().map(ProductDto::new).collect(Collectors.toList());
    }

}
