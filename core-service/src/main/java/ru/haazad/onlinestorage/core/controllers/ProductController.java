package ru.haazad.onlinestorage.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.haazad.onlinestorage.api.dtos.ProductDto;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.core.models.Product;
import ru.haazad.onlinestorage.core.services.ProductService;
import ru.haazad.onlinestorage.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private static final int PAGE_SIZE = 10;

    private final ProductService productService;
    private final Converter converter;

    @GetMapping
    public Page<ProductDto> showAllProducts(@RequestParam(defaultValue = "1", name = "p") int pageIndex,
                                            @RequestParam MultiValueMap<String, String> params) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAllProduct(pageIndex - 1, PAGE_SIZE, params).map(converter::productToDto);
    }

    @GetMapping("/{id}")
    public ProductDto showProduct(@PathVariable Long id) {
        return converter.productToDto(productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found")));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return converter.productToDto(productService.createProduct(setProduct(productDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping
    public void modifyProduct(@RequestBody ProductDto productDto) {
        Product product = setProduct(productDto);
        product.setId(productDto.getId());
        productService.modifyProduct(product);
    }

    @GetMapping("/filter")
    public List<ProductDto> filterProductByPrice(@RequestParam(required = false) Float minPrice, @RequestParam(required = false) Float maxPrice) {
        if (minPrice != null || maxPrice != null) {
            return productService.filterProductByPrice(minPrice, maxPrice).stream().map(converter::productToDto).collect(Collectors.toList());
        }
        return productService.findAllProduct().stream().map(converter::productToDto).collect(Collectors.toList());
    }

    private Product setProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
