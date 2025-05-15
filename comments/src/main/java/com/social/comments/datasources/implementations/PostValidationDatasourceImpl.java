package com.social.comments.datasources.implementations;

import com.social.comments.datasources.PostValidationDatasource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PostValidationDatasourceImpl implements PostValidationDatasource {

    private final RestTemplate restTemplate;

    @Override
    public boolean validatePostId(Long postId) {
        String url = "http://POST-DETAILS/posts/" + postId + "/validate";
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
            return response.getStatusCode().value() == 200;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Post validation error: " + e.getMessage(), e);
        }
    }
}
