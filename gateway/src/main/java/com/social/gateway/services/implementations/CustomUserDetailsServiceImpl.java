package com.social.gateway.services.implementations;

import com.social.gateway.model.UserAuthEntity;
import com.social.gateway.repos.UserAuthRepos;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private UserAuthRepos userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuthEntity> possibleUser = userRepository.findByUsername(username);
        if (possibleUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(possibleUser.get().getUsername(), possibleUser.get().getPassword(), new ArrayList<>());
    }
}