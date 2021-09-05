package com.upc.hydroti.security.application.services;

import com.upc.hydroti.security.infra.entity.UserEntity;
import com.upc.hydroti.security.infra.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String query) throws UsernameNotFoundException {
        if (query.isBlank()) {
            throw new UsernameNotFoundException("Invalid user.");
        }

        UserEntity userEntity = userRepository.findByEmailOrId(query.trim()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Given user (%s) not found.", query.trim())));

        return User.builder()
                .username(userEntity.getId())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRole())
                .build();
    }
}
