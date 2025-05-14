package com.social.posts_details.controllers;

import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.posts_details.dtos.GetPostsByUserResponse;
import com.social.posts_details.services.PostDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PostDetailsController {

    private final PostDetailsService postDetailsService;

    @GetMapping("/user/posts")
    public ResponseEntity<GetPostsByUserResponse> getPostsByUser(HttpServletRequest request){
        return ResponseEntity.ok(postDetailsService.getPostsByUserId(Long.parseLong(request.getHeader("id"))));
    }

    @DeleteMapping("/posts")
    public ResponseEntity<PostDeleteResponse> deletePost(HttpServletRequest request, @RequestBody PostDeleteRequest postDeleteRequest){
        return ResponseEntity.ok(postDetailsService.deletePost(postDeleteRequest, Long.parseLong(request.getHeader("id"))));
    }
}
