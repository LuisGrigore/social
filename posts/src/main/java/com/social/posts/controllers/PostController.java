package com.social.posts.controllers;

import com.social.posts.dtos.RegisterPostRequest;
import com.social.posts.dtos.RegisterPostResponse;
import com.social.posts.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegisterPostResponse> registerPost(HttpServletRequest request, @RequestParam MultipartFile file) throws Exception {
        RegisterPostRequest registerPostRequest = new RegisterPostRequest(file);
        try {
            return ResponseEntity.ok(postService.registerPost(registerPostRequest, Long.parseLong(request.getHeader("id"))));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{objectName}")
    public void getPostImage(@PathVariable String objectName, HttpServletResponse response) {
        try {
            InputStream inputStream = postService.getPostImage(objectName);
            StreamUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving file from MinIO", e);
        }
    }
}
