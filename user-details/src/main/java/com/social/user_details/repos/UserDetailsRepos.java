package com.social.user_details.repos;

import com.social.user_details.model.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepos extends JpaRepository<UserDetailsEntity, Long> {
}
