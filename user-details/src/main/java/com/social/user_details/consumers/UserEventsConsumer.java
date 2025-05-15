package com.social.user_details.consumers;

import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;
import com.social.common.exceptions.UserDuplicateException;
import com.social.common.exceptions.UserNotFoundException;
import com.social.user_details.services.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserEventsConsumer {

    private final UserDetailsService userDetailsService;

    @KafkaListener(
            topics = "user.create",
            groupId = "user-details",
            containerFactory = "userCreateListenerContainerFactory"
    )
    public void consumeUserCreateEvent(UserCreateEvent userCreateEvent){
        try {
            userDetailsService.saveUserDetails(userCreateEvent);
        }catch (UserDuplicateException userDuplicateException)
        {
            log.error(userDuplicateException.getMessage(), userDuplicateException);
        }
    }

    @KafkaListener(
            topics = "user.delete",
            groupId = "user-details",
            containerFactory = "userDeleteListenerContainerFactory"
    )
    public void consumeUserDeleteEvent(UserDeleteEvent userDeleteEvent){
        try {
            userDetailsService.deleteUserDetails(userDeleteEvent);
        }catch (UserNotFoundException userNotFoundException)
        {
            log.error(userNotFoundException.getMessage(), userNotFoundException);
        }
    }
}
