package com.social.comments.services.implementations;

import com.social.comments.datasources.PostValidationDatasource;
import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;
import com.social.comments.repos.CommentRepos;
import com.social.comments.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepos commentRepos;
    private final PostValidationDatasource postValidationDatasource;

    @Override
    public CommentCreationResponse createComment(CommentCreationRequest commentCreationRequest, Long id) {
        postValidationDatasource.validatePostId(commentCreationRequest.postId());
        return null;
    }
}
