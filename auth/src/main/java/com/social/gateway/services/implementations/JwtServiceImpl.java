package com.social.gateway.services.implementations;

import com.social.common.dtos.ValidateRequest;
import com.social.common.dtos.ValidateResponse;
import com.social.common.exceptions.TokenInvalidException;
import com.social.gateway.dtos.AuthenticationRequest;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.model.UserAuthEntity;
import com.social.gateway.services.JwtService;
import com.social.gateway.services.UserService;
import com.social.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public String createJwtToken(AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(), authenticationRequest.password()));

        final Optional<UserAuthEntity> userDetails = userService.findByUsername(authenticationRequest.username());
        if(userDetails.isEmpty())
            throw new UserNotFoundException("User " + authenticationRequest.username() + " not found.");
        return jwtUtil.generateToken(userDetails.get());
    }

    @Override
    public ValidateResponse validate(ValidateRequest validateRequest) throws TokenInvalidException {
        try {
            final String token = validateRequest.jwt();
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtUtil.validateToken(token, userDetails)) {
                throw new TokenInvalidException("Invalid token signature or expiration.");
            }

            Claims claims = jwtUtil.extractAllClaims(token);
            Long id = claims.get("id", Long.class);
            if (id == null) {
                throw new TokenInvalidException("Token does not contain user ID.");
            }

            return new ValidateResponse(id, username);
        } catch (Exception ex) {
            throw new TokenInvalidException(ex.getMessage());
        }
    }
}
