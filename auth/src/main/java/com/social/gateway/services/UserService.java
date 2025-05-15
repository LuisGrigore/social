package com.social.gateway.services;

import com.social.gateway.dtos.DeleteAcountRequest;
import com.social.gateway.dtos.DeleteAcountResponse;
import com.social.gateway.dtos.RegisterUserRequest;
import com.social.gateway.dtos.RegisterUserResponse;
import com.social.common.exceptions.UserDuplicateException;
import com.social.gateway.model.UserAuthEntity;

import java.util.Optional;

public interface UserService {
     Optional<UserAuthEntity> findByUsername(String username) ;
     RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws UserDuplicateException;
     DeleteAcountResponse deleteAcount(DeleteAcountRequest deleteAcountRequest, Long id);
}
