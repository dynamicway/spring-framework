package me.spring.web.transaction.order;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    void increaseOrderQuantityInParallelWithoutTransaction(int quantity) throws InterruptedException {
        Order order = orderRepository.findById(1L).orElseThrow();

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < quantity; i++) {
            executorService.submit(() -> {
                order.increaseQuantity();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        orderRepository.save(order);
    }

    long getOrderQuantity() {
        return orderRepository.findById(1L).orElseThrow().getQuantity();
    }

    void createOrder() {
        orderRepository.save(new Order());
    }

}
