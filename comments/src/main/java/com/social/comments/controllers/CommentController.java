package com.social.comments.controllers;

import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;
import com.social.comments.dtos.GetCommentsByPostResponse;
import com.social.comments.exceptions.PostNotFoundException;
import com.social.comments.services.CommentService;
import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.exceptions.DuplicateException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleDuplicateException(PostNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentCreationResponse> postComment(HttpServletRequest request, @RequestBody CommentCreationRequest commentCreationRequest){
        return ResponseEntity.ok(commentService.createComment(commentCreationRequest, Long.parseLong(request.getHeader("id"))));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<GetCommentsByPostResponse> getCommentsByPost(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getByPost(postId));
    }
}
