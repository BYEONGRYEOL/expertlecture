package com.sparta.springauth.controller;

import com.sparta.springauth.dto.ProductRequestDto;
import com.sparta.springauth.entity.User;
import com.sparta.springauth.entity.UserRoleEnum;
import com.sparta.springauth.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/validation")
    @ResponseBody
    // 데이터 검증을 하고싶은 Dto 객체에 앞의 Valid 어노테이션을 붙여줘야, 객체 변수들에 지정해놓은 Valid 과녈ㄴ 어노테이션 대로 테스트
    public ProductRequestDto testValid(@RequestBody @Valid ProductRequestDto requestDto) {
        return requestDto;
    }
}