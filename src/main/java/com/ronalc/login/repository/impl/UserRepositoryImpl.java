package com.ronalc.login.repository.impl;

import com.ronalc.login.model.Users;
import com.ronalc.login.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<Users> database = new ArrayList<>();

    @Override
    public Optional<Users> findByUsername(String username) {
        return database.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
    @Override
    public void save(Users user) {
        database.add(user);
    }
    public List<String> getAllUsernames() {
        return database.stream()
                .map(Users::getUsername)
                .collect(Collectors.toList());
    }
}