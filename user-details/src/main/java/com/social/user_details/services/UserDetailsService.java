package com.social.user_details.services;
import com.social.common.dtos.UserCreateEvent;
import com.social.common.dtos.UserDeleteEvent;

public interface UserDetailsService {
    void saveUserDetails(UserCreateEvent userCreateEvent);
    void deleteUserDetails(UserDeleteEvent userDeleteEvent);
}
