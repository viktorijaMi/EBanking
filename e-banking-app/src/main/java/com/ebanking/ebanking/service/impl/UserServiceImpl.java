package com.ebanking.ebanking.service.impl;

import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.User;
import com.ebanking.ebanking.model.enumerations.Role;
import com.ebanking.ebanking.model.exceptions.PasswordsDoNotMatchException;
import com.ebanking.ebanking.model.exceptions.UserNotFoundException;
import com.ebanking.ebanking.repository.UserRepository;
import com.ebanking.ebanking.service.PrimaryAccountService;
import com.ebanking.ebanking.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PrimaryAccountService primaryAccountService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PrimaryAccountService primaryAccountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.primaryAccountService = primaryAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> login(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password))
                .orElseThrow(() -> new UserNotFoundException(username));
        return Optional.of(user);
    }

    @Override
    public Optional<User> register(String username, String password, String repeatedPassword, String firstName, String lastName, String email, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (!password.equals(repeatedPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        Random random = new Random();
        PrimaryAccount primaryAccount = this.primaryAccountService.createAccount(username, random.nextInt(Integer.MAX_VALUE), 0.0);
        User user = new User(username, passwordEncoder.encode(password), firstName, lastName, email, role, primaryAccount);
        this.userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s)
                .orElseThrow(() -> new UserNotFoundException(s));
    }
}
