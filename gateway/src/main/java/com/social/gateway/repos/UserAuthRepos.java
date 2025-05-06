package com.social.gateway.repos;

import com.social.gateway.model.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepos extends JpaRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByUsername(String username);
}