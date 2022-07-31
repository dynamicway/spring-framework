package me.spring.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic exceptionHandlingTopic() {
        return new NewTopic("exception-handling", 3, (short) 1);
    }

}
