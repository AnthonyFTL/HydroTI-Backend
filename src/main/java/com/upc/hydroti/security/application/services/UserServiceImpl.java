package com.upc.hydroti.security.application.services;

import com.upc.hydroti.security.infra.entity.UserEntity;
import com.upc.hydroti.security.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }
}
