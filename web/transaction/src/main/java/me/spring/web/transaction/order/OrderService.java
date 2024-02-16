package me.spring.web.transaction.order;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    void increaseOrderQuantityInParallelWithTransactionTemplate(int increasedCount) throws InterruptedException {
        Order order = orderRepository.findById(1L).orElseThrow();

        ExecutorService executorService = Executors.newFixedThreadPool(increasedCount);
        CountDownLatch countDownLatch = new CountDownLatch(increasedCount);

        for (int i = 0; i < increasedCount; i++) {
            executorService.submit(() -> {
                order.increaseQuantity();
                countDownLatch.countDown();
            });
        }

        executorService.awaitTermination(increasedCount, TimeUnit.SECONDS);

        orderRepository.save(order);
    }

}
