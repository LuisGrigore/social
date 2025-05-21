package com.social.feed.services.implementations;

import com.social.common.events.PostCreateEvent;
import com.social.feed.datasources.FollowServiceDatasource;
import com.social.feed.dtos.GetFeedRepose;
import com.social.feed.model.FeedEntity;
import com.social.feed.repos.FeedRepos;
import com.social.feed.services.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepos feedRepos;
    private final FollowServiceDatasource followServiceDatasource;

    @Override
    public GetFeedRepose getByUser(Long id) {
        List<Long> feedPosts = feedRepos.getByOwner(id).stream()
                .map(FeedEntity::getPost)
                .toList();
        return new GetFeedRepose(feedPosts);
    }

    @Override
    public void deleteByOwner(Long id) {
        feedRepos.deleteByOwner(id);
    }

    @Override
    public void addPostToFeeds(PostCreateEvent postCreateEvent) {
        List<Long> followers = followServiceDatasource.getFollowers(postCreateEvent.owner()).stream()
                .forEach(id -> feedRepos.save(FeedEntity.builder()
                                .post(postCreateEvent.id)
                        .build()));
    }
}
