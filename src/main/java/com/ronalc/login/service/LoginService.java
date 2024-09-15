package com.ronalc.login.service;

import com.ronalc.login.repository.UserRepository;
import com.ronalc.login.util.PasswordUtils;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        String encryptedPassword = PasswordUtils.encryptPassword(password);

        return Option.ofOptional(userRepository.findByUsername(username))
                .map(user -> user.getPassword().equals(encryptedPassword))
                .getOrElse(false);
    }
}