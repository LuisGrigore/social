package com.social.comments.controllers;

import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;
import com.social.comments.services.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentCreationResponse> postComment(HttpServletRequest request, @RequestBody CommentCreationRequest commentCreationRequest){
        return ResponseEntity.ok(commentService.createComment(commentCreationRequest, Long.parseLong(request.getHeader("id"))));
    }
}
