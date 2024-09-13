package com.ronalc.login.service;

import com.ronalc.login.model.Users;
import com.ronalc.login.repository.UserRepository;
import com.ronalc.login.util.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private PasswordUtils passwordUtils;

    @BeforeEach
    public void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        Users testUser = new Users("ronaldo", encryptedPassword);
        Mockito.when(userRepository.findByUsername("ronaldo")).thenReturn(Optional.of(testUser));
        Mockito.when(userRepository.findByUsername("ronaldo")).thenReturn(Optional.empty());
    }


    @Test
    public void testLoginSuccess() {
        String username = "ronaldo";
        String rawPassword = "password";
        String encryptedPassword = PasswordUtils.encryptPassword(rawPassword);
        Users testUser = new Users(username, encryptedPassword);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
        boolean result = loginService.login(username, rawPassword);
        assertTrue(result);
    }

    @Test
    public void testLoginFail() {
        String username = "ronaldo";
        String rawPassword = "password";

        boolean result = loginService.login(username, rawPassword);
        assertFalse(result);
    }

    @Test
    public void testLoginWrongPassword() {
        String username = "ronaldo";
        String wrongPassword = "11password";

        boolean result = loginService.login(username, wrongPassword);
        assertFalse(result);
    }

    @Test
    public void testLoginUserNotFound() {
        String username = "usuarionaoexistente";
        String rawPassword = "password";

        boolean result = loginService.login(username, rawPassword);
        assertFalse(result);
    }
}