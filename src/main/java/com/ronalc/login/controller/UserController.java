package com.ronalc.login.controller;

import com.ronalc.login.service.LoginService;
import com.ronalc.login.service.RegisterUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (loginService.login(username, password)) {
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Credenciais inválidas");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        try {
            registerUserService.register(username, password);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao registrar usuário");
            return "register";
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }
}
