package ru.haazad.onlinestorage.webapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.haazad.onlinestorage.webapp.dto.ProductDto;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;
import ru.haazad.onlinestorage.webapp.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAllProduct() {
        List<Product> productList = productRepository.findAll();
        return createProductDtoList(productList);
    }

    @Override
    public ProductDto findProductById(Long id) {
        return new ProductDto(productRepository.findById(id).get());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> deleteProductById(Long id) {
        productRepository.deleteById(id);
        return findAllProduct();
    }

    // Как корректно пробросить исключение в случае отсутсвия обязательных параметров?
    @Override
    public List<ProductDto> filterProduct(Float minPrice, Float maxPrice) throws IllegalArgumentException{
        if (minPrice == null) {
            return createProductDtoList(productRepository.findAllByPriceLessThanEqual(maxPrice));
        }
        if (maxPrice == null) {
            return createProductDtoList(productRepository.findAllByPriceGreaterThanEqual(minPrice));
        }
        if (minPrice != null && maxPrice != null) {
            return createProductDtoList(productRepository.findAllByPriceBetween(minPrice, maxPrice));
        }
        throw new IllegalArgumentException("Required parameters for the filter are not specified");
    }

    private List<ProductDto> createProductDtoList(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product p: productList) {
            productDtoList.add(new ProductDto(p));
        }
        return productDtoList;
    }
}
