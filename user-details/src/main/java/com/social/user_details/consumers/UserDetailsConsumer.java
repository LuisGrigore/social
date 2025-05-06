package com.social.user_details.consumers;

import com.social.common.dtos.UserCreateEvent;
import com.social.user_details.exceptions.DuplicateUserDetailsException;
import com.social.user_details.services.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserDetailsConsumer {

    private final UserDetailsService userDetailsService;

    @KafkaListener(topics = "user.create", groupId = "user-details")
    public void consumeUserDetailsCreateEvent(UserCreateEvent userCreateEvent){
        try {
            userDetailsService.saveUserDetails(userCreateEvent);
        }catch (DuplicateUserDetailsException duplicateUserDetailsException)
        {
            log.error(duplicateUserDetailsException.getMessage(), duplicateUserDetailsException);
        }
    }
}
