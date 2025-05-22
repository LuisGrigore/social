package com.social.posts_details.producers;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.common.events.PostDetailsCreatedEvent;


public interface PostEventProducer {
    void producePostDeleteEvent(PostDeleteEvent postDeleteEvent);
    void producePostDetailsCreatedEvent(PostDetailsCreatedEvent event);
}
