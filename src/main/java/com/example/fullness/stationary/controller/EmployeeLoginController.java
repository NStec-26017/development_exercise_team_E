package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.form.LoginForm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * BP002【担当者ログイン画面】用 コントローラー
 * URL: /admin/login
 *
 */
@Controller
@Valid
// @RequestMapping("/admin/login")
public class EmployeeLoginController {
    /**
     * ログイン画面表示
     * 
     */
    @GetMapping("/admin/login")
    public String adminLogin(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "アカウント名またはパスワードが正しくありません");
        }
        return "admin/login";
    }

    /**
     * ログイン処理・入力チェック
     */
    @PostMapping("/admin/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 入力チェック
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errorMessage", String.join(" ", errorMessages));
            redirectAttributes.addFlashAttribute("form", form);

            // ログイン画面にリダイレクト
            return "redirect:/admin/login";
        }

        // バリデーション通過 → Spring Security 認証エンドポイントにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/authenticate");
        dispatcher.forward(request, response);
        return null;
    }
}
