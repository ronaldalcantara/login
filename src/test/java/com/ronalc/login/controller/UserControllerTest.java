package com.ronalc.login.controller;

import com.ronalc.login.service.LoginService;
import com.ronalc.login.service.RegisterUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private LoginService loginService;

    @Mock
    private RegisterUserService registerUserService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessLogin_Success() {
        when(loginService.login("validUser", "validPass")).thenReturn(true);
        String viewName = userController.processLogin("validUser", "validPass", model);
        assertEquals("redirect:/welcome", viewName);
        verify(loginService, times(1)).login("validUser", "validPass");
        verifyNoMoreInteractions(loginService);
    }

    @Test
    void testProcessLogin_InvalidCredentials() {
        when(loginService.login("invalidUser", "invalidPass")).thenReturn(false);
        String viewName = userController.processLogin("invalidUser", "invalidPass", model);
        assertEquals("login", viewName);
        verify(model, times(1)).addAttribute("error", "Credenciais inválidas");
        verify(loginService, times(1)).login("invalidUser", "invalidPass");
    }

    @Test
    void testProcessRegister_Success() {
        doNothing().when(registerUserService).register("validUser", "validPass");
        String viewName = userController.processRegister("validUser", "validPass", model);
        assertEquals("register", viewName); // Retorno para a página de registro
        verify(model, times(1)).addAttribute("success", "Usuário registrado com sucesso! <a href='/login'>Clique aqui para fazer login</a>");
        verify(registerUserService, times(1)).register("validUser", "validPass");
    }

    @Test
    void testProcessRegister_Exception() {
        doThrow(new RuntimeException("Erro ao registrar")).when(registerUserService).register("invalidUser", "invalidPass");
        String viewName = userController.processRegister("invalidUser", "invalidPass", model);
        assertEquals("register", viewName);
        verify(model, times(1)).addAttribute("error", "Erro ao registrar usuário");
        verify(registerUserService, times(1)).register("invalidUser", "invalidPass");
    }

    @Test
    void testShowRegisterPage() {
       String viewName = userController.showRegisterPage();
        assertEquals("register", viewName);
    }

    @Test
    void testShowWelcomePage() {
        String viewName = userController.showWelcomePage();
        assertEquals("welcome", viewName);
    }

    @Test
    void testShowLoginPage() {
        String viewName = userController.showLoginPage();
        assertEquals("login", viewName);
    }
}