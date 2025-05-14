package com.social.comments.services.implementations;

import com.social.comments.datasources.PostValidationDatasource;
import com.social.comments.dtos.CommentCreationRequest;
import com.social.comments.dtos.CommentCreationResponse;
import com.social.comments.dtos.GetCommentsByPostResponse;
import com.social.comments.model.CommentEntity;
import com.social.comments.repos.CommentRepos;
import com.social.comments.services.CommentService;
import com.social.comments.dtos.CommentGetDto;
import com.social.comments.exceptions.PostNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepos commentRepos;
    private final PostValidationDatasource postValidationDatasource;

    @Override
    public CommentCreationResponse createComment(CommentCreationRequest commentCreationRequest, Long id) {
        if (!postValidationDatasource.validatePostId(commentCreationRequest.postId()))
            throw new RuntimeException("Post not found");
        CommentEntity savedComment = commentRepos.save(CommentEntity.builder()
                        .owner(id)
                        .post(commentCreationRequest.postId())
                        .content(commentCreationRequest.content())
                .build());
        return new CommentCreationResponse(savedComment.getContent());
    }

    @Override
    public void deleteByPostId(Long id) {
        commentRepos.findByPost(id)
                .forEach(commentRepos::delete);
    }

    @Override
    public void deleteByOwnerId(Long id) {
        commentRepos.findByOwner(id)
                .forEach(commentRepos::delete);
    }

    @Override
    public GetCommentsByPostResponse getByPost(Long postId) throws  PostNotFoundException{
        List<CommentGetDto> commentGetDtos = commentRepos.findByPost(postId).stream()
                .map(commentEntity -> new CommentGetDto(commentEntity.getContent(), commentEntity.getOwner()))
                .toList();
        if (commentGetDtos.isEmpty())
            throw new PostNotFoundException("Post with id :" + postId + "not found.");
        return new GetCommentsByPostResponse(commentGetDtos);
    }
}
