package com.sparta.springauth.controller;

import com.sparta.springauth.entity.User;
import com.sparta.springauth.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ProductController {



    @GetMapping("/products")
    public String getProducts(
            //Authentication 객체의 Principal 부분을 가져온다.
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Authentication 의 principle
        User user = userDetails.getUser();
        return "redirect:/";
    }
}