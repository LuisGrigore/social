package com.social.posts.producers.implementations;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.posts.producers.PostEventProducer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostEventProducerImpl implements PostEventProducer {

    private final KafkaTemplate<String, PostCreateEvent> kafkaPostCreateTemplate;
    private final KafkaTemplate<String, PostDeleteEvent> kafkaPostDeleteTemplate;

    public void producePostCreateEvent(PostCreateEvent postCreateEvent){
        kafkaPostCreateTemplate.send("post.create", postCreateEvent);
    }

    public void producePostDeleteEvent(PostDeleteEvent postDeleteEvent) {
        kafkaPostDeleteTemplate.send("post.delete", postDeleteEvent);
    }
}
