package com.social.gateway.services;

import com.social.common.dtos.UserValidationRequest;
import com.social.common.dtos.UserValidationResponse;

import com.social.common.exceptions.TokenInvalidException;
import com.social.gateway.dtos.AuthenticationRequest;
import com.social.gateway.exceptions.UserNotFoundException;
import org.springframework.security.core.AuthenticationException;

public interface JwtService {
    String createJwtToken(AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException;

    UserValidationResponse validate(UserValidationRequest validateRequest) throws TokenInvalidException;
}
