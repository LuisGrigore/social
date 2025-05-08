package com.social.posts.producers;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;


public interface PostEventProducer {
    void producePostCreateEvent(PostCreateEvent postCreateEvent);
    void producePostDeleteEvent(PostDeleteEvent postDeleteEvent);
}
