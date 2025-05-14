package com.social.posts_details.producers;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;


public interface PostEventProducer {
    void producePostDeleteEvent(PostDeleteEvent postDeleteEvent);
}
