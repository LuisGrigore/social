package com.social.comments.consummers;

import com.social.comments.services.CommentService;
import com.social.common.events.PostDeleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostEventConsumer {

    private final CommentService commentService;

    @KafkaListener(
            topics = "post.delete",
            groupId = "comments",
            containerFactory = "postDeleteListenerContainerFactory"
    )
    public void consumePostDeleteEvent(PostDeleteEvent postDeleteEvent){
        commentService.deleteByPostId(postDeleteEvent.id());
    }
}