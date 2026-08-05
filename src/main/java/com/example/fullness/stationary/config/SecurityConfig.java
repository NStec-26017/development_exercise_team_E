package com.example.fullness.stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * ログイン認証制御とログアウト制御を行うクラス。
 * パスワードのハッシュ化を行う。
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
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin", "/logout").authenticated()
                        .anyRequest().denyAll())

                // ログインにかかわる情報
                .formLogin(login -> login
                        // ログイン時のPost先URL
                        .loginProcessingUrl("/authenticate")
                        // ログイン画面表示URL
                        .loginPage("/login")
                        // 認証成功時に表示するページ
                        .defaultSuccessUrl("/admin")
                        // 認証失敗時のリダイレクト先
                        .failureUrl("/login").permitAll())

                // ログアウトにかかわる情報
                .logout(logout -> logout
                        // ログアウト時のURL（Post先）
                        .logoutUrl("/logout")
                        // ログアウト成功時のリダイレクト先
                        .logoutSuccessUrl("/login")
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
