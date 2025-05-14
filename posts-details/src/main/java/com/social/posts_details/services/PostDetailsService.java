package com.social.posts_details.services;

import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.common.events.PostCreateEvent;
import com.social.posts_details.dtos.GetPostsByUserResponse;
import com.social.posts_details.exceptions.PostNotFoundException;

public interface PostDetailsService {
    void savePostDetails(PostCreateEvent postCreateEvent);
    PostDeleteResponse deletePost(PostDeleteRequest postDeleteRequest, Long id);

    void deletePostsByOwnerId(Long owner_id);

    GetPostsByUserResponse getPostsByUserId(Long id);

    void validatePost(Long id);
}
