package com.social.gateway.services;

import com.social.gateway.dtos.AuthenticationRequest;
import com.social.gateway.exceptions.not_found.UserNotFoundException;
import org.springframework.security.core.AuthenticationException;

public interface JwtService {
    String createJwtToken(AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException;
}
