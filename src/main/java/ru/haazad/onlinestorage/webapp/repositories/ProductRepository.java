package ru.haazad.onlinestorage.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetween(Float minPrice, Float maxPrice);
    List<Product> findAllByPriceLessThanEqual(Float maxPrice);
    List<Product> findAllByPriceGreaterThanEqual(Float minPrice);
}
