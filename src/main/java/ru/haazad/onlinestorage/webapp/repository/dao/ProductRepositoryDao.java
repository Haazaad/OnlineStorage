package ru.haazad.onlinestorage.webapp.repository.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.webapp.config.DatabaseConnection;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class ProductRepositoryDao implements ProductRepository {
    private final DatabaseConnection databaseConnection;

    @Autowired
    public ProductRepositoryDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Product> findAllProduct() {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return productList.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toUnmodifiableList());
        }
    }

    @Override
    public Product findProduct(Long id) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = (Product) session.createQuery("select p from Product p where p.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void addProduct(Product product) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void changePrice(Long id, Float diff) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("update Product p set p.price = p.price + :difference where p.id = :id")
                    .setParameter("difference", diff)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
