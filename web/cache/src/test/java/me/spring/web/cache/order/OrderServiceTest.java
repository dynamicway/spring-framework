package me.spring.web.cache.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService sut;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        repository.atomicLong.set(0);
        cacheClear();
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
        invokeConcurrently(() -> sut.getOrderAmount(1));
        // Cache stampede test - expects multiple calls during concurrent load
        assertThat(repository.atomicLong.get()).isNotEqualTo(1);
    }

    @Test
    void prevent_cache_stampede() {
        invokeConcurrently(() -> sut.getOrderAmountByPreventCacheStampede(1));

        assertThat(repository.atomicLong.get()).isEqualTo(1);
    }

    @Test
    void refresh_after_write() throws InterruptedException {
        sut.getOrderAmountByLoadingCache(1);
        assertThat(repository.atomicLong.get()).isEqualTo(1);

        // start refresh
        Thread.sleep(600);
        long startTime1 = System.currentTimeMillis();
        sut.getOrderAmountByLoadingCache(1);
        invokeConcurrently(() -> sut.getOrderAmountByLoadingCache(1));
        long duration1 = System.currentTimeMillis() - startTime1;
        assertThat(duration1).isLessThan(100);
        assertThat(repository.atomicLong.get()).isEqualTo(1);

        Thread.sleep(120);
        // refreshed
        assertThat(repository.atomicLong.get()).isEqualTo(2);

        // Still using refreshed cache - expireAfterWrite not reached
        Thread.sleep(300);
        long startTime2 = System.currentTimeMillis();
        invokeConcurrently(() -> sut.getOrderAmountByLoadingCache(1));
        long duration2 = System.currentTimeMillis() - startTime2;
        assertThat(duration2).isLessThan(100);
        assertThat(repository.atomicLong.get()).isEqualTo(2);
    }

    private Void invokeConcurrently(Supplier<Long> supplier) {
        return CompletableFuture.allOf(IntStream.range(0, 1000)
                .mapToObj(i1 -> CompletableFuture.supplyAsync(supplier))
                .toList().toArray(new CompletableFuture[0])).join();
    }

    private void cacheClear() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
    }

}