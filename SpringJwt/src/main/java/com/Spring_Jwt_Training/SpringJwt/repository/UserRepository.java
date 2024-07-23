package com.Spring_Jwt_Training.SpringJwt.repository;

import com.Spring_Jwt_Training.SpringJwt.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User , Integer> {
    Optional<User> findByEmail(String email);
}
