package com.social.gateway.producers;

import com.social.common.dtos.UserCreateEvent;
import com.social.common.dtos.UserDeleteEvent;

public interface UserEventProducer {
    void produceUserCreateEvent(UserCreateEvent userCreateEvent);
    void produceUserDeleteEvent(UserDeleteEvent userDeleteEvent);
}
