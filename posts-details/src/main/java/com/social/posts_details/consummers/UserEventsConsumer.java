package com.social.posts_details.consummers;

import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;
import com.social.posts_details.services.PostDetailsService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventsConsumer {

    private final PostDetailsService postDetailsService;


    @KafkaListener(
            topics = "user.delete",
            groupId = "post-details",
            containerFactory = "userDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(UserDeleteEvent userDeleteEvent){
        postDetailsService.deletePostsByOwnerId(userDeleteEvent.id());
    }
}
