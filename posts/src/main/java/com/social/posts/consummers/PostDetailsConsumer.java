package com.social.posts.consummers;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.posts.services.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PostDetailsConsumer {

    private final PostService postService;


    @KafkaListener(
            topics = "post.delete",
            groupId = "post",
            containerFactory = "postDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(PostDeleteEvent postDeleteEvent){
        postService.deletePost(postDeleteEvent);
    }
}
