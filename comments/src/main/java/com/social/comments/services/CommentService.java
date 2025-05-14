package com.social.comments.services;

import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;
import com.social.comments.dtos.GetCommentsByPostResponse;
import com.social.comments.exceptions.PostNotFoundException;

public interface CommentService {
    CommentCreationResponse createComment(CommentCreationRequest commentCreationRequest, Long id);

    void deleteByPostId(Long id);

    void deleteByOwnerId(Long id);

    GetCommentsByPostResponse getByPost(Long postId) throws PostNotFoundException;
}
