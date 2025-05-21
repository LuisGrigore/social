package com.social.feed.datasources.implementations;

import com.social.common.dtos.FollowersByUserResponse;
import com.social.feed.datasources.FollowServiceDatasource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FollowServiceDatasourceImpl implements FollowServiceDatasource {

    private final RestTemplate restTemplate;

    @Override
    public List<Long> getFollowers(Long userId) {
        String url = "http://FOLLOW/user/followers";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("id", userId.toString());

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<FollowersByUserResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    FollowersByUserResponse.class
            );

            return Objects.requireNonNull(response.getBody()).followedIds();
        }catch (Exception e) {
            throw new RuntimeException("User validation error: " + e.getMessage(), e);
        }
    }
}
