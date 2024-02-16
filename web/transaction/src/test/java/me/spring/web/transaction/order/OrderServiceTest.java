package me.spring.web.transaction.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService sut;

    @Test
    void increase_count_without_transaction() throws InterruptedException {
        sut.createOrder();

        sut.increaseOrderQuantityInParallelWithoutTransaction(10000);

        assertThat(sut.getOrderQuantity()).isLessThan(10000);
    }

}
