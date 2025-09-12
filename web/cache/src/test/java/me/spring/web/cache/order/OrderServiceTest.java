package me.spring.web.cache.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService sut;

    @Autowired
    private OrderRepository repository;

    @Test
    void cached() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10)
                .mapToObj(i -> executorService.submit(() -> sut.getOrderAmount(1)))
                .map(longFuture -> {
                    try {
                        return longFuture.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toUnmodifiableList());

        assertThat(repository.atomicLong.get()).isEqualTo(1);
    }

}