package com.social.posts.producers;

import com.social.common.events.PostCreateEvent;


public interface PostEventProducer {
    void producePostCreateEvent(PostCreateEvent postCreateEvent);
}
