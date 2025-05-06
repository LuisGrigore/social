package com.social.user_details.services.implementations;

import com.social.common.dtos.UserCreateEvent;
import com.social.common.dtos.UserDeleteEvent;
import com.social.user_details.exceptions.DuplicateUserDetailsException;
import com.social.user_details.exceptions.NotFoundUserDetailsException;
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
    public void saveUserDetails(UserCreateEvent userCreateEvent) throws DuplicateUserDetailsException {
        if(userDetailsRepos.findById(userCreateEvent.id()).isPresent())
            throw new DuplicateUserDetailsException("User with id" + userCreateEvent.id() + " already exists.");
        userDetailsRepos.save(userDetailsMapper.toUserDetailsEntity(userCreateEvent));
    }

    @Override
    public void deleteUserDetails(UserDeleteEvent userDeleteEvent) throws NotFoundUserDetailsException {
        if(userDetailsRepos.findById(userDeleteEvent.id()).isEmpty())
            throw new NotFoundUserDetailsException("User with id" + userDeleteEvent.id() + " not found.");
        userDetailsRepos.deleteById(userDeleteEvent.id());
    }
}
