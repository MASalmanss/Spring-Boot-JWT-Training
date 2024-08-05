package com.Spring_Jwt_Training.SpringJwt.service;

import com.Spring_Jwt_Training.SpringJwt.dto.LoginUserDto;
import com.Spring_Jwt_Training.SpringJwt.dto.RegisterUserDto;
import com.Spring_Jwt_Training.SpringJwt.entity.Role;
import com.Spring_Jwt_Training.SpringJwt.entity.User;
import com.Spring_Jwt_Training.SpringJwt.enums.RoleEnum;
import com.Spring_Jwt_Training.SpringJwt.repository.RoleRepository;
import com.Spring_Jwt_Training.SpringJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;



    public User signUp(RegisterUserDto userDto){
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new  User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());
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
