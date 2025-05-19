package com.social.feed.consummers;

import com.social.common.events.UserDeleteEvent;
import com.social.feed.services.FeedService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventsConsumer {

    private final FeedService feedService;


    @KafkaListener(
            topics = "user.delete",
            groupId = "feed",
            containerFactory = "userDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(UserDeleteEvent userDeleteEvent){
        feedService.deleteByOwner(userDeleteEvent.id());
    }
}
