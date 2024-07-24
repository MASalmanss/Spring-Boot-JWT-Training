package com.Spring_Jwt_Training.SpringJwt.service;

import com.Spring_Jwt_Training.SpringJwt.dto.LoginUserDto;
import com.Spring_Jwt_Training.SpringJwt.dto.RegisterUserDto;
import com.Spring_Jwt_Training.SpringJwt.entity.User;
import com.Spring_Jwt_Training.SpringJwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(RegisterUserDto userDto){
        User user = new  User();
        user.setFullName(user.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
        ));
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
