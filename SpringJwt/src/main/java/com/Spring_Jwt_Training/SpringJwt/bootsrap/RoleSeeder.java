package com.Spring_Jwt_Training.SpringJwt.bootsrap;

import com.Spring_Jwt_Training.SpringJwt.entity.Role;
import com.Spring_Jwt_Training.SpringJwt.enums.RoleEnum;
import com.Spring_Jwt_Training.SpringJwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles(){
        RoleEnum[] roles = new RoleEnum[] {RoleEnum.ADMIN, RoleEnum.USER , RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );
        Arrays.asList(roles).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
            optionalRole.ifPresentOrElse(System.out::println , ()->{
                Role role = new Role();
                role.setName(roleName);
                role.setDescription(roleDescriptionMap.get(roleName));
                roleRepository.save(role);
            });
        });
    }
}
