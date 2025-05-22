package com.social.posts_details.producers.implementations;

import com.social.common.events.PostDeleteEvent;
import com.social.common.events.PostDetailsCreatedEvent;
import com.social.posts_details.producers.PostEventProducer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostEventProducerImpl implements PostEventProducer {

    private final KafkaTemplate<String, PostDeleteEvent> kafkaPostDeleteTemplate;
    private final KafkaTemplate<String, PostDetailsCreatedEvent> kafkaPostDetailsCreatedTemplate;


    public void producePostDeleteEvent(PostDeleteEvent postDeleteEvent) {
        kafkaPostDeleteTemplate.send("post.delete", postDeleteEvent);
    }

    @Override
    public void producePostDetailsCreatedEvent(PostDetailsCreatedEvent event) {
        kafkaPostDetailsCreatedTemplate.send("post.details.created", event);
    }
}
