package com.social.comments.repos;

import com.social.comments.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepos extends JpaRepository<CommentEntity, Long> {
}
