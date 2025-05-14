package com.social.comments.consummers;

import com.social.comments.services.CommentService;
import com.social.common.events.UserDeleteEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventsConsumer {

    private final CommentService commentService;

    @KafkaListener(
            topics = "user.delete",
            groupId = "comments",
            containerFactory = "userDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(UserDeleteEvent userDeleteEvent){
        commentService.deleteByOwnerId(userDeleteEvent.id());
    }
}
