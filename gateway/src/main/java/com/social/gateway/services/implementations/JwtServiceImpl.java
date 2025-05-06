package com.social.gateway.services.implementations;

import com.social.gateway.dtos.AuthenticationRequest;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.model.UserAuthEntity;
import com.social.gateway.services.JwtService;
import com.social.gateway.services.UserService;
import com.social.gateway.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public String createJwtToken(AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password()));

        final Optional<UserAuthEntity> userDetails = userService.findByUsername(authenticationRequest.username());
        if(userDetails.isEmpty())
            throw new UserNotFoundException("User " + authenticationRequest.username() + " not found.");
        return jwtUtil.generateToken(userDetails.get());
    }
}
