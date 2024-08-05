package com.Spring_Jwt_Training.SpringJwt.bootsrap;

import com.Spring_Jwt_Training.SpringJwt.dto.RegisterUserDto;
import com.Spring_Jwt_Training.SpringJwt.entity.Role;
import com.Spring_Jwt_Training.SpringJwt.entity.User;
import com.Spring_Jwt_Training.SpringJwt.enums.RoleEnum;
import com.Spring_Jwt_Training.SpringJwt.repository.RoleRepository;
import com.Spring_Jwt_Training.SpringJwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            this.createSuperAdmin();
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
    private void createSuperAdmin() throws BadRequestException {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setEmail("superadmin@gmail.com");
        userDto.setPassword("12345");
        userDto.setFullName("Super Admin");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isEmpty()) {
            User user = new User();
            user.setFullName(userDto.getFullName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(optionalRole.get());
            userRepository.save(user);
        }



    }
}
