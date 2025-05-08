package com.social.gateway.services.implementations.mappers;

import com.social.common.events.UserCreateEvent;
import com.social.gateway.dtos.RegisterUserRequest;
import com.social.gateway.dtos.RegisterUserResponse;
import com.social.gateway.model.UserAuthEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Mapper(componentModel = "spring")
public abstract class UserAuthMapper {

    public abstract UserCreateEvent toUserCreateEvent(UserAuthEntity userAuthEntity);

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAuthEntity toUserAuthEntity(RegisterUserRequest registerUserRequest) {
        return UserAuthEntity.builder()
                .username(registerUserRequest.username())
                .role(registerUserRequest.role())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .build();
    }

    public RegisterUserResponse toRegisterUserResponse(UserAuthEntity userAuthEntity){
        return new RegisterUserResponse(userAuthEntity.getUsername(), Instant.now().toString());
    }
}
