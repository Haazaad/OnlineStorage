package ru.haazad.onlinestorage.webapp.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.haazad.onlinestorage.webapp.models.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUsername(String username);

    @Query(nativeQuery = true, value = "select case when count(o) > 0 then true else false end " +
            "from orders o " +
            "where o.user_user_id = :userId " +
            "and o.order_id in (select i.order_order_id from order_items i where i.product_product_id = :productId)")
    boolean hasOrder(@Param("userId") Long userId, @Param("productId") Long productId);
}
