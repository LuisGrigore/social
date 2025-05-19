package com.social.feed.datasources.implementations;

import com.social.feed.datasources.FollowServiceDatasource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowServiceDatasourceImpl implements FollowServiceDatasource {

    private final RestTemplate restTemplate;

    @Override
    public List<Long> getFollowers(Long userId) {
        String url = "http://USER-DETAILS/users/" + userId + "/validate";
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
        }catch (Exception e) {
            throw new RuntimeException("User validation error: " + e.getMessage(), e);
        }
    }
}
