package com.social.gateway.controllers;

import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.exceptions.DuplicateException;
import com.social.gateway.dtos.DeleteAcountRequest;
import com.social.gateway.dtos.DeleteAcountResponse;
import com.social.gateway.exceptions.UserNotFoundException;
import com.social.gateway.services.UserService;
import com.social.gateway.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
@Slf4j
public class AcountController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

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

    @DeleteMapping
    public ResponseEntity<DeleteAcountResponse> deleteAcount(HttpServletRequest request, @RequestBody DeleteAcountRequest deleteAcountRequest) throws Exception{
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new Exception("a");

        String jwt = authorizationHeader.substring(7);
        String id = jwtUtil.extractUsername(jwt);

        return ResponseEntity.ok( userService.deleteAcount(deleteAcountRequest,jwtUtil.extractClaim(jwt, claims -> claims.get("id", Long.class))));
    }
}
