package ru.haazad.onlinestorage.webapp.repository.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.config.DatabaseConnection;
import ru.haazad.onlinestorage.webapp.model.Order;
import ru.haazad.onlinestorage.webapp.model.User;
import ru.haazad.onlinestorage.webapp.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryDao implements OrderRepository {

    private final DatabaseConnection databaseConnection;

    @Autowired
    public OrderRepositoryDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Order> getAllOrders() {
        try (Session session = databaseConnection.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Order> orders = session.createQuery("from Order").getResultList();
            System.out.println(orders);
            session.getTransaction().commit();
            return orders.stream().sorted(Comparator.comparing(Order::getId)).collect(Collectors.toUnmodifiableList());
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Order> orders = session.createQuery("from Order o join fetch o.user where o.user.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            System.out.println(orders);
            session.getTransaction().commit();
            return orders.stream().sorted(Comparator.comparing(Order::getId)).collect(Collectors.toUnmodifiableList());
        }
    }

    @Override
    public List<User> getUsersByProductId(Long productId) {
        try (Session session = databaseConnection.getFactory().getCurrentSession()){
            session.beginTransaction();
            List<Order> orders = session.createQuery("from Order o join fetch o.product where o.product = :id")
                    .setParameter("id", productId)
                    .getResultList();
            System.out.println(orders);
            session.getTransaction().commit();
            List<User> users = new ArrayList<>();
            for (Order o: orders) {
                users.add(o.getUser());
            }
            System.out.println(users);
            return users;
        }
    }


}
