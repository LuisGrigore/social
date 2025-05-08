package com.social.posts.services;

import com.social.posts.dtos.RegisterPostRequest;
import com.social.posts.dtos.RegisterPostResponse;

public interface PostService {
    RegisterPostResponse registerPost(RegisterPostRequest registerPostRequest) throws Exception;
}
