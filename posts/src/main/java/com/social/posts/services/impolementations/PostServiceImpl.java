package com.social.posts.services.impolementations;

import com.social.common.events.PostCreateEvent;
import com.social.common.events.PostDeleteEvent;
import com.social.posts.dtos.RegisterPostRequest;
import com.social.posts.dtos.RegisterPostResponse;
import com.social.posts.producers.PostEventProducer;
import com.social.posts.services.PostService;
import io.minio.*;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostEventProducer postEventProducer;
    private final MinioClient minioClient;
    private final String BUCKET_NAME = "posts";

    @Override
    public RegisterPostResponse registerPost(RegisterPostRequest registerPostRequest, Long owner) throws Exception {

        createBucketIfNotExists(BUCKET_NAME);

        String postName = "post_id:" + UUID.randomUUID() + "-post_name:" + registerPostRequest.file().getOriginalFilename();
        uploadToMinIO(registerPostRequest.file(), postName, BUCKET_NAME);
        String contentUrl = "http://localhost:8080/posts/" + postName;
        postEventProducer.producePostCreateEvent(new PostCreateEvent(contentUrl, owner, postName));
        return new RegisterPostResponse(contentUrl);
    }

    @Override
    public InputStream getPostImage(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .build());
    }

    @Override
    public void deletePost(PostDeleteEvent postDeleteEvent) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(postDeleteEvent.postName())
                            .build()
            );
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    private void createBucketIfNotExists(String bucketName) {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            throw new RuntimeException("Bucket error.", e);
        }
    }

    private void uploadToMinIO(MultipartFile file, String objectName, String bucketName) throws Exception {
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
        }
    }

}
