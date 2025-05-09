package com.social.posts.services.impolementations;

import com.social.common.events.PostCreateEvent;
import com.social.posts.dtos.RegisterPostRequest;
import com.social.posts.dtos.RegisterPostResponse;
import com.social.posts.producers.PostEventProducer;
import com.social.posts.services.PostService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostEventProducer postEventProducer;
    private final MinioClient minioClient;

    @Override
    public RegisterPostResponse registerPost(RegisterPostRequest registerPostRequest, Long owner) throws Exception {
        final String bucketName = "posts";

        createBucketIfNotExists(bucketName);

        String objectName = "post-" + UUID.randomUUID() + "-" + registerPostRequest.file().getOriginalFilename();
        uploadToMinIO(registerPostRequest.file(), objectName, bucketName);
        String contentUrl = "http://localhost:9000/" + bucketName + "/" + objectName;
        postEventProducer.producePostCreateEvent(new PostCreateEvent(contentUrl, owner));
        return new RegisterPostResponse(contentUrl);
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
