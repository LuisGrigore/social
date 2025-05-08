package com.social.gateway.producers;

import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;

public interface UserEventProducer {
    void produceUserCreateEvent(UserCreateEvent userCreateEvent);
    void produceUserDeleteEvent(UserDeleteEvent userDeleteEvent);
}
