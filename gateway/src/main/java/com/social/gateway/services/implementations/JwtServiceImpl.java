package com.social.gateway.services.implementations;

import com.social.gateway.dtos.AuthenticationRequest;
import com.social.gateway.exceptions.not_found.UserNotFoundException;
import com.social.gateway.services.JwtService;
import com.social.gateway.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public String createJwtToken(AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username());
        return jwtUtil.generateToken(userDetails);
    }
}
