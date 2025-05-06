package com.social.user_details.services;
import com.social.common.dtos.UserCreateEvent;

public interface UserDetailsService {
    void saveUserDetails(UserCreateEvent userCreateEvent);
}
