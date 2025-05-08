package com.social.gateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic genreCreationTopic(){
        return TopicBuilder
                .name("user.create")
                .build();
    }
    @Bean
    public NewTopic contentTypeCreationTopic(){
        return TopicBuilder
                .name("user.delete")
                .build();
    }
}
