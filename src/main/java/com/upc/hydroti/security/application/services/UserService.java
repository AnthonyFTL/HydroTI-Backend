package com.upc.hydroti.security.application.services;

import com.upc.hydroti.security.infra.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity addUser(UserEntity user);


}
