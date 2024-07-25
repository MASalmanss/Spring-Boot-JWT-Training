package com.Spring_Jwt_Training.SpringJwt.service;

import com.Spring_Jwt_Training.SpringJwt.entity.User;
import com.Spring_Jwt_Training.SpringJwt.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        List<User> users =new ArrayList<>();
         userRepository.findAll().forEach(users::add);
        return users;
    }
}
