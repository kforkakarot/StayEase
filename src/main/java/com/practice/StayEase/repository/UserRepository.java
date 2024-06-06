package com.practice.StayEase.repository;


import com.practice.StayEase.entity.Role;
import com.practice.StayEase.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
