package me.spring.web.cache.order;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
class OrderService {
    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "orderNumber")
    public long getOrderAmount(long orderNumber) {
        return repository.getOrderAmount(orderNumber);
    }

}
