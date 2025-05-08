package com.social.gateway.services;

import com.social.gateway.dtos.DeleteAcountRequest;
import com.social.gateway.dtos.DeleteAcountResponse;
import com.social.gateway.dtos.RegisterUserRequest;
import com.social.gateway.dtos.RegisterUserResponse;
import com.social.gateway.exceptions.DuplicateUserException;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.model.UserAuthEntity;

import java.util.Optional;

public interface UserService {
     Optional<UserAuthEntity> findByUsername(String username) ;
     RegisterUserResponse register(RegisterUserRequest registerUserRequest) throws DuplicateUserException;
     DeleteAcountResponse deleteAcount(DeleteAcountRequest deleteAcountRequest, Long id);
}
