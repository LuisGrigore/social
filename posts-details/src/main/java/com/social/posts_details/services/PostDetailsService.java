package com.social.posts_details.services;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;

public interface PostDetailsService {
    void savePostDetails(PostCreateEvent postCreateEvent);

    void deletePostDetails(PostDeleteEvent postDeleteEvent);
}
