package com.social.gateway.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.social.common.dtos.UserCreateEvent;
import com.social.common.dtos.UserDeleteEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule());
    }

    public Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    public <T> ProducerFactory<String, T> producerFactory(Class<T> valueType){
        Map<String, Object> configProps = producerConfig();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public <T> KafkaTemplate<String, T> kafkaTemplate(Class<T> valueType){
        ProducerFactory<String, T> producerFactory = producerFactory(valueType);
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, UserCreateEvent> userCreateEventKafkaTemplate() {
        return kafkaTemplate(UserCreateEvent.class);
    }
    @Bean
    public KafkaTemplate<String, UserDeleteEvent> userDeleteEventKafkaTemplate() {
        return kafkaTemplate(UserDeleteEvent.class);
    }
}
