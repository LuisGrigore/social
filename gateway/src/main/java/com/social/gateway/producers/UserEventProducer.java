package com.social.gateway.producers;

import com.social.common.dtos.UserCreateEvent;

public interface UserEventProducer {
    void produceUserCreateEvent(UserCreateEvent userCreateEvent);

}
