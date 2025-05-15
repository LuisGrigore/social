package com.social.follows.consummers;

import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;
import com.social.follows.services.FollowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventsConsumer {

    private final FollowService followService;


    @KafkaListener(
            topics = "user.delete",
            groupId = "follows",
            containerFactory = "userDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(UserDeleteEvent userDeleteEvent){
        followService.deleteByUserId(userDeleteEvent.id());
    }
}
