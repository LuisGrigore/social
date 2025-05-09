package com.social.posts_details.repos;

import com.social.posts_details.model.PostDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailsRepos extends JpaRepository<PostDetailsEntity, Long> {
}
