package me.spring.web.cache.order;

import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicLong;

@Repository
class OrderRepository {
    public final AtomicLong atomicLong = new AtomicLong(0);

    long getOrderAmount(long orderNumber) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        atomicLong.incrementAndGet();
        return orderNumber;
    }

}
