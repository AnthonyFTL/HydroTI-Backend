package com.upc.hydroti.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.hydroti.security.application.dto.AddUserRequest;
import com.upc.hydroti.security.application.services.UserService;
import com.upc.hydroti.security.infra.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping()
    public void addUser(@RequestBody AddUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // UserEntity new ObjectMapper().readValue(request, UserEntity.class);
        UserEntity user = new UserEntity(randomUUID().toString(), request.getEmail(), encodedPassword,
                request.getRole(), request.getLastname(), request.getName());
        userService.addUser(user);
    }

}
