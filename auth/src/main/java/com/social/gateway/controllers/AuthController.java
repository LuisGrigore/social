package com.social.gateway.controllers;

import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.dtos.UserValidationRequest;
import com.social.common.dtos.UserValidationResponse;
import com.social.common.exceptions.DuplicateException;
import com.social.common.exceptions.TokenInvalidException;
import com.social.gateway.dtos.*;
import com.social.gateway.exceptions.DuplicateUserException;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.services.JwtService;
import com.social.gateway.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final UserService userService;

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiExceptionResponse> handleDuplicateException(DuplicateException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.CONFLICT.value()));
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiExceptionResponse> handleAuthenticationException(AuthenticationException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.UNAUTHORIZED.value()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleUserNotFoundException(UserNotFoundException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<ApiExceptionResponse> handleTokenInvalidException(TokenInvalidException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.UNAUTHORIZED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException, UserNotFoundException {
        final String jwt = jwtService.createJwtToken(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) throws DuplicateUserException {
        return ResponseEntity.ok(userService.register(registerUserRequest));
    }
    @PostMapping("/validate")
    public ResponseEntity<UserValidationResponse> validate(@RequestBody UserValidationRequest validateRequest) throws TokenInvalidException {
        return ResponseEntity.ok(jwtService.validate(validateRequest));
    }
}
