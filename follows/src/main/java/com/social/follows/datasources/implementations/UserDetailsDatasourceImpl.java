package com.social.follows.datasources.implementations;

import com.social.follows.datasources.UserDetailsDatasource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserDetailsDatasourceImpl implements UserDetailsDatasource {

    private final RestTemplate restTemplate;

    @Override
    public boolean validateUserId(Long userId) {
        String url = "http://USER-DETAILS/users/" + userId + "/validate";
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
            return response.getStatusCode().value() == 200;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("User validation error: " + e.getMessage(), e);
        }
    }
}
