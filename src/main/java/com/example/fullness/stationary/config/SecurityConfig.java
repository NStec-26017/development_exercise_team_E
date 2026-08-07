package com.example.fullness.stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * パスワードのハッシュ化を行い、ログイン認証制御とログアウト制御を行うクラス。
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
        /**
         * セキュリティルールを記載。
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(authz -> authz
                                .requestMatchers("/public/**", "/css/**", "/", "/admin", "/admin/login",
                                                "/admin/account/**")
                                .permitAll()
                                .anyRequest().authenticated());
                // .formLogin(login -> login
                // .loginProcessingUrl("/admin/login")
                // .loginPage("/admin/login")
                // .defaultSuccessUrl("/admin", true)
                // .failureUrl("/admin/login").permitAll())
                // .logout(logout -> logout
                // .logoutUrl("/admin/logout")
                // .logoutSuccessUrl("/admin/login")
                // .invalidateHttpSession(true)
                // .clearAuthentication(true)
                // .deleteCookies("JSESSIONID"));
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
