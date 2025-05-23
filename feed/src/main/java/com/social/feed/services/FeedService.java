package com.social.feed.services;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDetailsCreatedEvent;
import com.social.feed.dtos.GetFeedRepose;

public interface FeedService {
    GetFeedRepose getByUser(Long id);

    void deleteByOwner(Long id);

    void addPostToFeeds(PostDetailsCreatedEvent postCreateEvent);
}
