package ru.haazad.onlinestorage.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
