package com.social.comments.services;

import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;

public interface CommentService {
    CommentCreationResponse createComment(CommentCreationRequest commentCreationRequest, Long id);
}
