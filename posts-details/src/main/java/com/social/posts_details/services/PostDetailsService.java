package com.social.posts_details.services;

import com.social.common.dtos.PostDeleteRequest;
import com.social.common.dtos.PostDeleteResponse;
import com.social.common.events.PostCreateEvent;

public interface PostDetailsService {
    void savePostDetails(PostCreateEvent postCreateEvent);
    PostDeleteResponse deletePost(PostDeleteRequest postDeleteRequest, Long id);
}
