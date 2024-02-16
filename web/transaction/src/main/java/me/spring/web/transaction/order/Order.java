package me.spring.web.transaction.order;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "order")
class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ColumnDefault("0")
    private long quantity;

    void increaseQuantity() {
        this.quantity++;
    }
}
