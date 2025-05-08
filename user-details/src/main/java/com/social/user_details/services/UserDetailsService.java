package com.social.user_details.services;
import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;

public interface UserDetailsService {
    void saveUserDetails(UserCreateEvent userCreateEvent);
    void deleteUserDetails(UserDeleteEvent userDeleteEvent);
}
