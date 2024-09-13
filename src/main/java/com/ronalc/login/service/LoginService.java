package com.ronalc.login.service;

import com.ronalc.login.model.Users;
import com.ronalc.login.repository.UserRepository;
import com.ronalc.login.util.PasswordUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean login(String username, String password) {
        String encryptedPassword = PasswordUtils.encryptPassword(password);
        Optional<Users> user = userRepository.findByUsername(username);
        return user.map(u -> u.getPassword().equals(encryptedPassword)).orElse(false);
    }
}