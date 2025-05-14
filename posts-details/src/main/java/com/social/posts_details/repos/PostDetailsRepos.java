package com.social.posts_details.repos;

import com.social.posts_details.model.PostDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDetailsRepos extends JpaRepository<PostDetailsEntity, Long> {
    List<PostDetailsEntity> findByOwner(Long postId);
}
