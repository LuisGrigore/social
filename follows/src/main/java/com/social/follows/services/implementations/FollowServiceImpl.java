package com.social.follows.services.implementations;

import com.social.common.exceptions.UserDuplicateException;
import com.social.common.exceptions.UserNotFoundException;
import com.social.follows.datasources.UserDetailsDatasource;
import com.social.common.dtos.FollowersByUserResponse;
import com.social.follows.exceptions.FollowDuplicateException;
import com.social.follows.exceptions.FollowNotFoundException;
import com.social.follows.model.FollowEntity;
import com.social.follows.repos.FollowRepos;
import com.social.follows.services.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final FollowRepos followRepos;
    private final UserDetailsDatasource userDetailsDatasource;

    @Override
    public void follow(Long followerId, Long followedId) throws UserNotFoundException{
        if(followerId.equals(followedId))
            throw new UserDuplicateException("Follower id and Followed id can't be the same.");
        if(followRepos.existsByFollowerIdAndFollowedId(followerId, followedId))
            throw new FollowDuplicateException("User with id: " + followerId + " already follows user with id: " + followedId + ".");
        if(!userDetailsDatasource.validateUserId(followedId))
            throw new UserNotFoundException("User with id: " + followedId + " not found.");
        followRepos.save(FollowEntity.builder()
                        .followedId(followedId)
                        .followerId(followerId)
                .build());
    }

    @Override
    public void unfollow(Long followerId, Long followedId) throws FollowNotFoundException{
        Optional<FollowEntity> followEntityOptional= followRepos.findByFollowerIdAndFollowedId(followerId, followedId);
        if(followEntityOptional.isEmpty())
            throw new FollowNotFoundException("User with id : " + followerId + " doesn't follow user with id : " + followedId + ".");
        followRepos.delete(followEntityOptional.get());
    }

    @Override
    public FollowersByUserResponse getFollowedByUser(Long followerId) {
        List<Long> followedIds = followRepos.getByFollowerId(followerId).stream()
                .map(FollowEntity::getFollowedId)
                .toList();
        return new FollowersByUserResponse(followedIds);
    }

    @Override
    public FollowersByUserResponse getFollowersByUser(Long followedId) {
        List<Long> followedIds = followRepos.getByFollowedId(followedId).stream()
                .map(FollowEntity::getFollowerId)
                .toList();
        return new FollowersByUserResponse(followedIds);
    }

    @Override
    public void deleteByUserId(Long userId) {
        followRepos.getByFollowerId(userId).forEach(followRepos::delete);
        followRepos.getByFollowedId(userId).forEach(followRepos::delete);
    }
}
