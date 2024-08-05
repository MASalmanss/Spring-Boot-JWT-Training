package com.Spring_Jwt_Training.SpringJwt.controller;

import com.Spring_Jwt_Training.SpringJwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;


}
