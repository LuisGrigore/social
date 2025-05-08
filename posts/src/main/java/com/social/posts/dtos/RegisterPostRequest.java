package com.social.posts.dtos;

import org.springframework.web.multipart.MultipartFile;

public record RegisterPostRequest(MultipartFile file) {
}
