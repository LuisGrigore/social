package com.social.comments.dtos;

public record CommentCreationRequest(Long postId, String content) {
}
