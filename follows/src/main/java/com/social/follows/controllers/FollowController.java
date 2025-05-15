package com.social.follows.controllers;

import com.social.follows.dtos.FollowersByUserResponse;
import com.social.follows.services.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Void> follow(HttpServletRequest request, @PathVariable Long followedID){
        followService.follow(Long.parseLong(request.getHeader("id")), followedID);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
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
