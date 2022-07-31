package me.spring.kafka.exceptionhandling;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageInitializeApi {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageInitializeApi(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/messages")
    public void initializeMessages() {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("exception-handling", String.valueOf(i));
        }
    }

}
