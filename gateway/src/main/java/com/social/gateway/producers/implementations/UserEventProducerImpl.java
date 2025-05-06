package com.social.gateway.producers.implementations;

import com.social.common.dtos.UserCreateEvent;
import com.social.gateway.producers.UserEventProducer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEventProducerImpl implements UserEventProducer {

    private final KafkaTemplate<String, UserCreateEvent> kafkaTemplate;

    public void produceUserCreateEvent(UserCreateEvent userCreateEvent){
        kafkaTemplate.send("user.create", userCreateEvent);
    }
}
