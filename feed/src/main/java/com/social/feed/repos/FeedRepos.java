package com.social.feed.repos;

import com.social.feed.model.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepos extends JpaRepository<FeedEntity, Long> {
    List<FeedEntity> getByOwner(Long id);
    void deleteByOwner(Long id);
}
