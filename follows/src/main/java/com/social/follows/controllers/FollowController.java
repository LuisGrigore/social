package com.social.follows.controllers;

import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.exceptions.DuplicateException;
import com.social.common.exceptions.NotFoundException;
import com.social.follows.dtos.FollowersByUserResponse;
import com.social.follows.services.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiExceptionResponse> handleDuplicateException(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.CONFLICT.value()));
    }

    @PostMapping("/follow/{followedID}")
    public ResponseEntity<Void> follow(HttpServletRequest request, @PathVariable Long followedID){
        followService.follow(Long.parseLong(request.getHeader("id")), followedID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/follow/{followedID}")
    public ResponseEntity<Void> unfollow(HttpServletRequest request, @PathVariable Long followedID){
        followService.unfollow(Long.parseLong(request.getHeader("id")), followedID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/followed")
    public ResponseEntity<FollowersByUserResponse> getFollowedByUser(HttpServletRequest request){
        return ResponseEntity.ok(followService.getFollowedByUser(Long.parseLong(request.getHeader("id"))));
    }

    @GetMapping("/user/followers")
    public ResponseEntity<FollowersByUserResponse> getFollowersByUser(HttpServletRequest request){
        return ResponseEntity.ok(followService.getFollowersByUser(Long.parseLong(request.getHeader("id"))));
    }
}
