package ru.haazad.onlinestorage.webapp.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.haazad.onlinestorage.webapp.models.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Float minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(Float maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), '%' + title + '%');
    }
}
