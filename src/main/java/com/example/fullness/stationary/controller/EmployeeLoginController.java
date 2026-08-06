package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BP002【担当者ログイン画面】用 コントローラー<br>
 * URL: /admin/login
 *
 */
@Controller
@RequestMapping("/admin")
public class EmployeeLoginController {
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping
    public String menu() {
        return "menu";
    }
}
