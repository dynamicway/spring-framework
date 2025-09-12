package me.spring.web.cache.order;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class OrderService {
    private final OrderRepository repository;
    LoadingCache<Long, Long> caffeine = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMillis(1000))
            .refreshAfterWrite(Duration.ofMillis(500))
            .build(this::getOrderAmount);


    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "orderNumber")
    public long getOrderAmount(long orderNumber) {
        return repository.getOrderAmount(orderNumber);
    }

    @Cacheable(value = "prevent_cache_stampede", sync = true)
    public long getOrderAmountByPreventCacheStampede(long orderNumber) {
        return repository.getOrderAmount(orderNumber);
    }

    public long getOrderAmountByLoadingCache(long orderNumber) {
        return caffeine.get(orderNumber);
    }

}
