package com.social.follows.repos;

import com.social.follows.model.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FollowRepos extends JpaRepository<FollowEntity, Long> {
    Optional<FollowEntity> findByFollowerIdAndFollowedId(Long followerId, Long followedId);
    Collection<FollowEntity> getByFollowerId(Long followerId);
    Collection<FollowEntity> getByFollowedId(Long followedId);
    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);
}
