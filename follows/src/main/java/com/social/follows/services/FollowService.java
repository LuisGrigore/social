package com.social.follows.services;

import com.social.common.exceptions.UserNotFoundException;
import com.social.common.dtos.FollowersByUserResponse;
import com.social.follows.exceptions.FollowNotFoundException;

public interface FollowService {
    void follow(Long followerId, Long followedID) throws UserNotFoundException;

    void unfollow(Long followerId, Long followedID) throws FollowNotFoundException;

    FollowersByUserResponse getFollowedByUser(Long followerId);

    FollowersByUserResponse getFollowersByUser(Long followedId);

    void deleteByUserId(Long id);
}
