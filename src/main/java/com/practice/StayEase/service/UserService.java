package com.practice.StayEase.service;

import com.practice.StayEase.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    User registerNewUser(User user);
    UserDetailsService userDetailsService();
    Optional<User> findUserByEmail(String email);
    User save(User user);
}
