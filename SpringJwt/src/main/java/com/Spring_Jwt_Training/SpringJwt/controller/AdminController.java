package com.Spring_Jwt_Training.SpringJwt.controller;

import com.Spring_Jwt_Training.SpringJwt.dto.RegisterUserDto;
import com.Spring_Jwt_Training.SpringJwt.entity.User;
import com.Spring_Jwt_Training.SpringJwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdmin(@RequestBody RegisterUserDto userDto) {
        User createAdmin = userService.createAdministrator(userDto);
        return ResponseEntity.ok(createAdmin);
    }
}
