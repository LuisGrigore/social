package com.social.posts_details.controllers;

import com.social.common.dtos.ApiExceptionResponse;
import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.common.exceptions.NotFoundException;
import com.social.common.exceptions.PostNotFoundException;
import com.social.posts_details.dtos.GetPostsByUserResponse;
import com.social.posts_details.services.PostDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotAuthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class PostDetailsController {

    private final PostDetailsService postDetailsService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotAuthorizedException(NotAuthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiExceptionResponse(ex.getMessage(), Instant.now().toString(), HttpStatus.UNAUTHORIZED.value()));
    }

    @GetMapping("/user/posts")
    public ResponseEntity<GetPostsByUserResponse> getPostsByUser(HttpServletRequest request){
        return ResponseEntity.ok(postDetailsService.getPostsByUserId(Long.parseLong(request.getHeader("id"))));
    }

    @DeleteMapping("/posts")
    public ResponseEntity<PostDeleteResponse> deletePost(HttpServletRequest request, @RequestBody PostDeleteRequest postDeleteRequest){
        return ResponseEntity.ok(postDetailsService.deletePost(postDeleteRequest, Long.parseLong(request.getHeader("id"))));
    }

    @GetMapping("/posts/{id}/validate")
    public ResponseEntity<Void> validatePost(@PathVariable Long id){
        if(!postDetailsService.existsById(id))
            throw new PostNotFoundException("Post with id: " + id + "not found");
        return ResponseEntity.ok().build();
    }
}
