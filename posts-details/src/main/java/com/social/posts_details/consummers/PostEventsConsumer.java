package com.social.posts_details.consummers;

import com.social.common.events.PostCreateEvent;
import com.social.posts_details.services.PostDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PostEventsConsumer {

    private final PostDetailsService postDetailsService;

    @KafkaListener(
            topics = "post.create",
            groupId = "post-details",
            containerFactory = "postCreateListenerContainerFactory"
    )
    public void consumeUserCreateEvent(PostCreateEvent postCreateEvent){
        postDetailsService.savePostDetails(postCreateEvent);
    }

}
