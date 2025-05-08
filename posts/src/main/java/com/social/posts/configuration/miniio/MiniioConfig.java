package com.social.posts.configuration.miniio;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiniioConfig {
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")  // URL de tu MinIO
                .credentials("minio", "minio123")
                .build();
    }
}
