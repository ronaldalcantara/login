package com.ronalc.login.repository;

import com.ronalc.login.model.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<Users> findByUsername(String username);
    void save(Users user);
}