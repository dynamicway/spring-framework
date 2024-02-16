package me.spring.web.transaction.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from `orders` where id = 1 for update ", nativeQuery = true)
    Order findOrder();
}
