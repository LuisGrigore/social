package com.social.comments.repos;

import com.social.comments.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepos extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByOwner(Long ownerId);
    List<CommentEntity> findByPost(Long post);
}
