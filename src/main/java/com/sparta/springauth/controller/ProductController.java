package com.sparta.springauth.controller;

import com.sparta.springauth.entity.User;
import com.sparta.springauth.entity.UserRoleEnum;
import com.sparta.springauth.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
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


    @Secured(UserRoleEnum.Authority.ADMIN)
    @GetMapping("/products/secured")
    public String getProductByAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("userDetails.getUsername() : "  + userDetails.getUsername());
        for(GrantedAuthority authority : userDetails.getAuthorities()){
            System.out.println(" authority.getAuthority() : " + authority.getAuthority());
        }
        return "redirect:/";
    }
}