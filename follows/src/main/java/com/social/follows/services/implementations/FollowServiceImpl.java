package com.social.follows.services.implementations;

import com.social.common.exceptions.UserNotFoundException;
import com.social.follows.datasources.UserDetailsDatasource;
import com.social.follows.dtos.FollowersByUserResponse;
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
    public void follow(Long followerId, Long followedID) throws UserNotFoundException{
        if(!userDetailsDatasource.validateUserId(followedID))
            throw new UserNotFoundException("User with id: " + followedID + " not found.");
        followRepos.save(FollowEntity.builder()
                        .followedId(followedID)
                        .followerId(followerId)
                .build());
    }

    @Override
    public void unfollow(Long followerId, Long followedID) throws FollowNotFoundException{
        Optional<FollowEntity> followEntityOptional= followRepos.findByFollowerIdAndFollowedId(followerId, followedID);
        if(followEntityOptional.isEmpty())
            throw new FollowNotFoundException("User with id : " + followerId + " doesn't follow user with id : " + followedID + ".");
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
