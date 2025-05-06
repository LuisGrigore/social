package com.social.user_details.services.implementations.mappers;

import com.social.common.dtos.UserCreateEvent;
import com.social.user_details.model.UserDetailsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserDetailsMapper {
    public abstract UserDetailsEntity toUserDetailsEntity(UserCreateEvent userCreateEvent);
}
