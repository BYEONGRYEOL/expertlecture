package com.sparta.springauth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity // Spring Security 지원을 가능하게 함
public class WebSecurityConfig_Session {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        // RESTFul 한 api를 제공하니까 diable해도 좋다.
        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        // css or js or 회원가입, 로그인 관련 api 접근시 resources 접근 허용 설정
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/api/user/**").permitAll() // /api/user/ 로시작하는 요청 허가
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        // 로그인 사용
        // 스프링 시큐리티 내부적으로 loginpage
        http.formLogin((formLogin)->
                formLogin
                        //각각 필터체인을 걸어두는건데,
                        //인증이 필요한 사용자의 Get 요청시 아래 나의 로그인페이지로
                        .loginPage("/api/user/login-page") // http GET 요청이 와서 loginPage로 이동시킴
                        .loginProcessingUrl("/api/user/login") // POST login post 요청 url 지정
                        // 주의 위의 url이 내 api와 같다고해서 내 로그인 api에 해당하는 함수가 실행되는 것이 아님

                        .defaultSuccessUrl("/")
                        .failureUrl("/api/user/login-page?error")
                        .permitAll()
        );

        return http.build();
    }
}



















