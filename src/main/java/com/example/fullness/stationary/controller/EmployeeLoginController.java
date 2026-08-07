package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BP002【担当者ログイン画面】用 コントローラー
 * URL: /admin/login
 *
 */
@Controller
@RequestMapping("/admin/login")
public class EmployeeLoginController {
    /**
     * BP003「アカウント登録(完了)」画面表示
     * 
     */
    @RequestMapping
    public String login() {
        return "admin/login";
    }

}
