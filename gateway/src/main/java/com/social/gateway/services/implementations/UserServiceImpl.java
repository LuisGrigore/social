package com.social.gateway.services.implementations;

import com.social.gateway.dtos.RegisterUserRequest;
import com.social.gateway.dtos.RegisterUserResponse;
import com.social.gateway.exceptions.duplicate.DuplicateUserException;
import com.social.gateway.model.UserAuthEntity;
import com.social.gateway.repos.UserAuthRepos;
import com.social.gateway.services.UserService;
import com.social.gateway.services.implementations.mappers.UserAuthMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAuthRepos userAuthRepos;

    private final UserAuthMapper userAuthMapper;


    @Override
    public Optional<UserAuthEntity> findByUsername(String username) {
        return userAuthRepos.findByUsername(username);
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws DuplicateUserException{
        if (userAuthRepos.findByUsername(registerUserRequest.username()).isPresent())
            throw new DuplicateUserException("User " + registerUserRequest.username() + "already exists.");

        return userAuthMapper.toRegisterUserResponse(userAuthRepos.save(userAuthMapper.toUserAuthEntity(registerUserRequest)));
    }
}
