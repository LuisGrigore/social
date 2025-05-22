package com.social.feed.consummers;

import com.social.common.events.PostDetailsCreatedEvent;
import com.social.feed.services.FeedService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PostEventsConsumer {

    private final FeedService feedService;

    @KafkaListener(
            topics = "post.details.created",
            groupId = "feed",
            containerFactory = "postDetailsCreatedListenerContainerFactory"
    )
    public void consumePostDetailsCreatedEvent(PostDetailsCreatedEvent postDetailsCreatedEvent){
        System.out.println("waaaaaaaaaaaaaaaaaaaaaa");
        feedService.addPostToFeeds(postDetailsCreatedEvent);
    }

}
