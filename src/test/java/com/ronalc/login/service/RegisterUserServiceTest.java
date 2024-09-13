package com.ronalc.login.service;

import com.ronalc.login.model.Users;
import com.ronalc.login.repository.UserRepository;
import com.ronalc.login.util.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class RegisterUserServiceTest {

    private RegisterUserService registerUserService;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        registerUserService = new RegisterUserService(userRepository);
    }

    @Test
    public void testRegisterUser() {
        String username = "ronaldo";
        String password = "password";
        String encryptedPassword = PasswordUtils.encryptPassword(password);
        registerUserService.register(username, password);
        verify(userRepository).save(new Users(username, encryptedPassword));
    }
}