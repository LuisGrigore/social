package com.social.user_details.controllers;

import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.exceptions.NotFoundException;
import com.social.user_details.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.NOT_FOUND.value()));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Void> validateUserId(@PathVariable Long userId){
        userDetailsService.validate(userId);
        return ResponseEntity.ok().build();
    }
}
