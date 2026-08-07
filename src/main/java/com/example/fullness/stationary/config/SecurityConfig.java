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

        http.authorizeHttpRequests(
                // アクセス制限の情報
                authz -> authz
                        .requestMatchers("/public/**", "/css/**", "/", "/admin", "/admin/login").permitAll()
                        .anyRequest().authenticated())

                // ログインにかかわる情報
                .formLogin(login -> login
                        // ログイン時のPost先URL
                        .loginProcessingUrl("/admin/login")
                        // ログイン画面表示URL
                        .loginPage("/admin/login")
                        // 認証成功時に表示するページ
                        .defaultSuccessUrl("/admin", true)
                        // 認証失敗時のリダイレクト先
                        .failureUrl("/admin/login").permitAll())

                // ログアウトにかかわる情報
                .logout(logout -> logout
                        // ログアウト時のURL（Post先）
                        .logoutUrl("/admin/logout")
                        // ログアウト成功時のリダイレクト先
                        .logoutSuccessUrl("/admin/login")
                        // セッションを破棄するか否か
                        .invalidateHttpSession(true)
                        // 認証情報をクリアするか否か
                        .clearAuthentication(true)
                        // 消すCookieの名前
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
