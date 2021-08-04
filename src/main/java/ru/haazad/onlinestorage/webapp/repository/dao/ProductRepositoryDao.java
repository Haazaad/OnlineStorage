package ru.haazad.onlinestorage.webapp.repository.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.haazad.onlinestorage.webapp.config.DatabaseConnection;
import ru.haazad.onlinestorage.webapp.model.Product;
import ru.haazad.onlinestorage.webapp.repository.ProductRepository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class ProductRepositoryDao implements ProductRepository {
    private DatabaseConnection databaseConnection;

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
            return Collections.unmodifiableList(productList.stream().sorted(Comparator.comparing(Product::getId)).collect(Collectors.toList()));
        }
    }

    @Override
    public Product findProduct(Long id) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("select p from Product p where p.id = :id");
            query.setParameter("id", id);
            Product product = (Product) query.getSingleResult();
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
    public void changeCoast(Long id, Float diff) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update Product p set p.coast = p.coast + :difference where p.id = :id");
            query.setParameter("difference", diff);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("delete Product p where p.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
