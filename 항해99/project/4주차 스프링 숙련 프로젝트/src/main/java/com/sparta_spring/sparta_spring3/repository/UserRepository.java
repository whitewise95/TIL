package com.sparta_spring.sparta_spring3.repository;

import com.sparta_spring.sparta_spring3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}