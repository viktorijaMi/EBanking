package com.ebanking.ebanking.service;

import com.ebanking.ebanking.model.User;
import com.ebanking.ebanking.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);
    Optional<User> login(String username, String password);
    Optional<User> register(String username, String password,String repeatedPassword, String firstName, String lastName, String email, Role role);
}
