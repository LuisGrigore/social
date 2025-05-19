package com.social.feed.consummers;

import com.social.common.events.PostCreateEvent;
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
            topics = "post.create",
            groupId = "feed",
            containerFactory = "postCreateListenerContainerFactory"
    )
    public void consumeUserCreateEvent(PostCreateEvent postCreateEvent){
        feedService.addPostToFeeds(postCreateEvent);
    }

}
