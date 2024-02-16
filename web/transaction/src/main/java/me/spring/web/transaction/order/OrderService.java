package me.spring.web.transaction.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
class OrderService {
    private final OrderRepository orderRepository;
    private final TransactionTemplate transactionTemplate;

    OrderService(OrderRepository orderRepository, TransactionTemplate transactionTemplate) {
        this.orderRepository = orderRepository;
        this.transactionTemplate = transactionTemplate;
    }

    void increaseOrderQuantityInParallelWithoutTransaction(int quantity) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(quantity);

        for (int i = 0; i < quantity; i++) {
            executorService.submit(() -> {
                Order order = orderRepository.findOrder();
                order.increaseQuantity();
                orderRepository.save(order);
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void increaseOrderQuantityInParallelWithTransactionManager(int quantity) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(quantity);

        for (int i = 0; i < quantity; i++) {
            executorService.submit(() -> transactionTemplate.execute(status -> {
                Order order = orderRepository.findOrder();
                order.increaseQuantity();
                orderRepository.save(order);
                countDownLatch.countDown();
                return null;
            }));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    long getOrderQuantity() {
        return orderRepository.findById(1L).orElseThrow().getQuantity();
    }

    void createOrder() {
        orderRepository.save(new Order());
    }
}
