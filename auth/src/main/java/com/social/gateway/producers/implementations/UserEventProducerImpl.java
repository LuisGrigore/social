package com.social.gateway.producers.implementations;

import com.social.common.dtos.UserCreateEvent;
import com.social.common.dtos.UserDeleteEvent;
import com.social.gateway.producers.UserEventProducer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEventProducerImpl implements UserEventProducer {

    private final KafkaTemplate<String, UserCreateEvent> kafkaUserCreateTemplate;
    private final KafkaTemplate<String, UserDeleteEvent> kafkaUserDeleteTemplate;

    @Override
    public void produceUserCreateEvent(UserCreateEvent userCreateEvent){
        kafkaUserCreateTemplate.send("user.create", userCreateEvent);
    }

    @Override
    public void produceUserDeleteEvent(UserDeleteEvent userDeleteEvent) {
        kafkaUserDeleteTemplate.send("user.delete", userDeleteEvent);
    }
}
