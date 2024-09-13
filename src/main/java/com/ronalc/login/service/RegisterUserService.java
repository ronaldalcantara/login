package com.ronalc.login.service;

import com.ronalc.login.model.Users;
import com.ronalc.login.repository.UserRepository;
import com.ronalc.login.util.PasswordUtils;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void register(String username, String password) {
        String encryptedPassword = PasswordUtils.encryptPassword(password);
        userRepository.save(new Users(username, encryptedPassword));
    }
}