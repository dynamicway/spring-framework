package me.spring.kafka.messageordering;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {
    public final List<Integer> messages = new ArrayList<>();

    public final CountDownLatch countDownLatch = new CountDownLatch(100);

    @KafkaListener(topics = "message-ordering", concurrency = "10")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord);
        messages.add(Integer.parseInt(consumerRecord.value()));
        countDownLatch.countDown();
    }
}
