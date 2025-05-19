package com.social.feed.datasources;

import java.util.List;

public interface FollowServiceDatasource {
    List<Long> getFollowers(Long userId);
}
