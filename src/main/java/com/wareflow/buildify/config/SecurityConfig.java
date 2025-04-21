package com.wareflow.buildify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // 🔐 비밀번호 암호화용 빈 등록 (회원가입, 로그인 비교 등에 사용)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔐 보안 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()  // ✅ 요거 중요!!
                //.antMatchers("/login", "/signup", "/css/**", "/js/**", "/images/**").permitAll()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(customSuccessHandler())
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        return http.build();
    }

    // ✅ 로그인 성공 시 ROLE에 따라 리다이렉트 처리
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Authentication authentication
            ) throws IOException {

                for (GrantedAuthority auth : authentication.getAuthorities()) {
                    String role = auth.getAuthority();

                    if ("ROLE_ADMIN".equals(role)) {
                        response.sendRedirect("/admin/pages/index");
                        return;
                    } else if ("ROLE_USER".equals(role)) {
                        response.sendRedirect("/users/pages/index");
                        return;
                    }
                }

                // 예외적으로 아무 권한도 없을 때
                response.sendRedirect("/login?error=no_role");
            }
        };
    }
}