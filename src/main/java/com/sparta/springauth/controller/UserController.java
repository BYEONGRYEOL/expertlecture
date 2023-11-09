package com.sparta.springauth.controller;

import com.sparta.springauth.dto.LoginRequest;
import com.sparta.springauth.dto.SignupRequest;
import com.sparta.springauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequest req){
        userService.signup(req);

        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequest req, HttpServletResponse res){
        try {
            userService.login(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/api/user/login-page?error"; // ? error는 client의 요청에 의해 붙임
        }
        return "redirect:/";
    }
}