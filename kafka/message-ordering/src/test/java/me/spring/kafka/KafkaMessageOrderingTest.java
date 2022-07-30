package me.spring.kafka;

import me.spring.kafka.messageordering.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.stream.IntStream;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 10, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaMessageOrderingTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Consumer consumer;

    @Test
    void not_ordering() throws InterruptedException {
        IntStream.range(0, 100).forEach(i -> kafkaTemplate.send("message-ordering", String.valueOf(i)));
        consumer.countDownLatch.await();
        boolean notOrdered = false;
        for (int i = 0; i < 99; i++) {
            if (consumer.messages.get(i) + 1 != consumer.messages.get(i + 1)) {
                notOrdered = true;
                break;
            }
        }

        assert consumer.messages.size() == 100;
        assert notOrdered;
    }
}
