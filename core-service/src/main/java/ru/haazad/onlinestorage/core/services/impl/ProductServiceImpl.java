package ru.haazad.onlinestorage.core.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.haazad.onlinestorage.api.dtos.ProductDto;
import ru.haazad.onlinestorage.api.exceptions.ResourceNotFoundException;
import ru.haazad.onlinestorage.core.models.Product;
import ru.haazad.onlinestorage.core.repositories.ProductRepository;
import ru.haazad.onlinestorage.core.repositories.specifications.ProductSpecifications;
import ru.haazad.onlinestorage.core.services.ProductService;
import ru.haazad.onlinestorage.core.utils.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Converter converter;

    private static final String FILTER_MIN_PRICE = "minPrice";
    private static final String FILTER_MAX_PRICE = "maxPrice";
    private static final String FILTER_TITLE = "title";

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllProduct(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Page<Product> findAllProduct(int pageIndex, int pageSize, MultiValueMap<String, String> params) {
        return productRepository.findAll(constructSpecification(params), PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void modifyProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> filterProductByPrice(Float minPrice, Float maxPrice) {
        if (minPrice == null) {
            return productRepository.findAllByPriceLessThanEqual(maxPrice);
        } else if (maxPrice == null) {
            return productRepository.findAllByPriceGreaterThanEqual(minPrice);
        } else {
            return productRepository.findAllByPriceBetween(minPrice, maxPrice);
        }
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(converter::productToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productRepository.findById(id).map(converter::productToDto).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
    }

    private Specification<Product> constructSpecification(MultiValueMap<String, String> params) {
        Specification<Product> specification = Specification.where(null);
        if (params.containsKey(FILTER_MIN_PRICE) && params.getFirst(FILTER_MIN_PRICE).isBlank()) {
            Float minPrice = Float.parseFloat(params.getFirst(FILTER_MIN_PRICE));
            specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey(FILTER_MAX_PRICE) && params.getFirst(FILTER_MAX_PRICE).isBlank()) {
            Float maxPrice = Float.parseFloat(params.getFirst(FILTER_MAX_PRICE));
            specification.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey(FILTER_TITLE) && params.getFirst(FILTER_TITLE).isBlank()) {
            String title = params.getFirst(FILTER_TITLE);
            specification.and(ProductSpecifications.titleLike(title));
        }
        return specification;
    }
}
