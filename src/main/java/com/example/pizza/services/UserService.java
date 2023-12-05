package com.example.pizza.services;

import com.example.pizza.models.Role;
import com.example.pizza.models.User;
import com.example.pizza.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean createUser(User user) {
        String name = user.getUsername();
        if (userRepository.findByUsername(user.getUsername()) != null) return false;
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        log.info("Saving new user with name {}", name);
        return true;
    }
}