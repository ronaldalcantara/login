package com.ronalc.login.controller;

import com.ronalc.login.service.LoginService;
import com.ronalc.login.service.RegisterUserService;
import io.vavr.control.Try;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    private final LoginService loginService;
    private final RegisterUserService registerUserService;

    public UserController(LoginService loginService, RegisterUserService registerUserService) {
        this.loginService = loginService;
        this.registerUserService = registerUserService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        return Optional.of(loginService.login(username, password))
                .filter(isAuthenticated -> isAuthenticated)
                .map(isAuthenticated -> "redirect:/welcome")
                .orElseGet(() -> {
                    model.addAttribute("error", "Credenciais inválidas");
                    return "login";
                });
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        return Try.of(() -> {
                    registerUserService.register(username, password);
                    model.addAttribute("success", "Usuário registrado com sucesso! <a href='/login'>Clique aqui para fazer login</a>");
                    return "register"; // Mantém na página de registro com mensagem de sucesso
                })
                .recover(Exception.class, e -> {
                    model.addAttribute("error", "Erro ao registrar usuário");
                    return "register";
                })
                .get();
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}
