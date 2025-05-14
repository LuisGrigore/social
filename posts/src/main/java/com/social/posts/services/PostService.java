package com.social.posts.services;

import com.social.common.events.PostDeleteEvent;
import com.social.posts.dtos.RegisterPostRequest;
import com.social.posts.dtos.RegisterPostResponse;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PostService {
    RegisterPostResponse registerPost(RegisterPostRequest registerPostRequest, Long owner) throws Exception;

    InputStream getPostImage(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    void deletePost(PostDeleteEvent postDeleteEvent);
}
