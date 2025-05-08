package com.social.gateway.services.implementations;

import com.social.common.events.UserDeleteEvent;
import com.social.gateway.dtos.DeleteAcountRequest;
import com.social.gateway.dtos.DeleteAcountResponse;
import com.social.gateway.dtos.RegisterUserRequest;
import com.social.gateway.dtos.RegisterUserResponse;
import com.social.gateway.exceptions.DuplicateUserException;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.exceptions.WrongPasswordException;
import com.social.gateway.model.UserAuthEntity;
import com.social.gateway.producers.UserEventProducer;
import com.social.gateway.repos.UserAuthRepos;
import com.social.gateway.services.UserService;
import com.social.gateway.services.implementations.mappers.UserAuthMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAuthRepos userAuthRepos;
    private final UserAuthMapper userAuthMapper;
    private final UserEventProducer userEventProducer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserAuthEntity> findByUsername(String username) {
        return userAuthRepos.findByUsername(username);
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws DuplicateUserException{
        if (userAuthRepos.findByUsername(registerUserRequest.username()).isPresent())
            throw new DuplicateUserException("User " + registerUserRequest.username() + "already exists.");
        UserAuthEntity userAuthEntity = userAuthRepos.save(userAuthMapper.toUserAuthEntity(registerUserRequest));
        userEventProducer.produceUserCreateEvent(userAuthMapper.toUserCreateEvent(userAuthEntity));
        return userAuthMapper.toRegisterUserResponse(userAuthEntity);
    }

    @Override
    public DeleteAcountResponse deleteAcount(DeleteAcountRequest deleteAcountRequest, Long id){
        Optional<UserAuthEntity> userAuthEntity = userAuthRepos.findById(id);
        if (userAuthEntity.isEmpty())
            throw new UserNotFoundException("User with id " + id + " not found.");
        if (!passwordEncoder.matches(deleteAcountRequest.password(),userAuthEntity.get().getPassword()))
            throw new WrongPasswordException("Wrong password");
        userAuthRepos.deleteById(id);
        userEventProducer.produceUserDeleteEvent(new UserDeleteEvent(id));
        return new DeleteAcountResponse(Instant.now().toString());
    }
}
