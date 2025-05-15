package com.social.user_details.controllers;

import com.social.user_details.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Void> validateUserId(@PathVariable Long userId){
        userDetailsService.validate(userId);
        return ResponseEntity.ok().build();
    }
}
