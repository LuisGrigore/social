package com.social.posts.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic genreCreationTopic(){
        return TopicBuilder
                .name("post.create")
                .build();
    }
    @Bean
    public NewTopic contentTypeCreationTopic(){
        return TopicBuilder
                .name("post.delete")
                .build();
    }
}
