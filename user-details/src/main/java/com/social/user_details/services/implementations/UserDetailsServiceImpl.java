package com.social.user_details.services.implementations;

import com.social.common.events.UserCreateEvent;
import com.social.common.events.UserDeleteEvent;
import com.social.common.exceptions.UserDuplicateException;
import com.social.common.exceptions.UserNotFoundException;
import com.social.user_details.repos.UserDetailsRepos;
import com.social.user_details.services.UserDetailsService;
import com.social.user_details.services.implementations.mappers.UserDetailsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepos userDetailsRepos;
    private final UserDetailsMapper userDetailsMapper;

    @Override
    public void saveUserDetails(UserCreateEvent userCreateEvent) throws UserDuplicateException {
        if(userDetailsRepos.findById(userCreateEvent.id()).isPresent())
            throw new UserDuplicateException("User with id" + userCreateEvent.id() + " already exists.");
        userDetailsRepos.save(userDetailsMapper.toUserDetailsEntity(userCreateEvent));
    }

    @Override
    public void deleteUserDetails(UserDeleteEvent userDeleteEvent) throws UserNotFoundException {
        if(userDetailsRepos.findById(userDeleteEvent.id()).isEmpty())
            throw new UserNotFoundException("User with id" + userDeleteEvent.id() + " not found.");
        userDetailsRepos.deleteById(userDeleteEvent.id());
    }

    @Override
    public void validate(Long userId) {
        if(!userDetailsRepos.existsById(userId))
            throw new UserNotFoundException("User with id: " + userId + "doesn't exist.");
    }
}
