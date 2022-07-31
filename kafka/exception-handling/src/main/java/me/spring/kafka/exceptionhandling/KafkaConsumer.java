package me.spring.kafka.exceptionhandling;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "exception-handling", groupId = "exception-handling-group")
    public void subscribe(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        if (consumerRecord.value().equals("3"))
            throw new RuntimeException();
        acknowledgment.acknowledge();
    }

}
