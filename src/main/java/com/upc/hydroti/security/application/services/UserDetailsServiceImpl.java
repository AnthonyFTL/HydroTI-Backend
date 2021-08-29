package com.upc.hydroti.security.application.services;

import com.upc.hydroti.security.infra.entity.UserEntity;
import com.upc.hydroti.security.infra.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isBlank()) {
            throw new UsernameNotFoundException("Invalid user.");
        }

        UserEntity userEntity = userRepository.findByUsername(username.trim()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Given user (%s) not found.", username.trim())));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(emptyList())
                .build();
    }
}
