package ru.haazad.onlinestorage.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(Float minPrice, Float maxPrice);
    List<Product> findAllByPriceLessThanEqual(Float maxPrice);
    List<Product> findAllByPriceGreaterThanEqual(Float minPrice);
}
