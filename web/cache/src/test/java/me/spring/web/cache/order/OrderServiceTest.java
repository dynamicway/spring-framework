package me.spring.web.cache.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService sut;

    @Autowired
    private OrderRepository repository;

    @BeforeEach
    void setUp() {
        repository.atomicLong.set(0);
    }

    @Test
    void cached() {
        List<CompletableFuture<Long>> futures = IntStream.range(0, 1000)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> sut.getOrderAmount(1)))
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // If cached, the call count would be 1,000 times.
        assertThat(repository.atomicLong.get()).isLessThan(1000);
    }

    @Test
    void cache_stampede() {
        List<CompletableFuture<Long>> futures = IntStream.range(0, 1000)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> sut.getOrderAmount(1)))
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Cache stampede test - expects multiple calls during concurrent load
        assertThat(repository.atomicLong.get()).isNotEqualTo(1);
    }

    @Test
    void prevent_cache_stampede() {
        List<CompletableFuture<Long>> futures = IntStream.range(0, 1000)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> sut.getOrderAmountByPreventCacheStampede(1)))
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        assertThat(repository.atomicLong.get()).isEqualTo(1);
    }

}