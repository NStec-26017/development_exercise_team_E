package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.fullness.stationary.form.CategoryForm;

/**
 * BP019【商品カテゴリ登録入力画面】用 コントローラー<br>
 * URL: /deptregistinput
 *
 * @author Fullness, Inc.
 *
 */

@Controller
public class ProductCategoryRegistInputServlet {

    // 画面を表示する(Get)
    @GetMapping("/admin/category/add")
    public String showPage(@PathVariable Integer id, Model model) {
        return "redirect:templates/admin/category/add"; // Thymeleafの画面名を返すだけ
    }

    // 完了ボタンが押されて保存する（Post）
    @PostMapping("/admin/category/add/confirm")
    public String register(@Validated CategoryForm form, BindingResult result) {

        // 【バリデーション】入力チェックに引っかかった場合
        if (result.hasErrors()) {
            // エラー情報を持ったまま、もう一度「入力画面」に戻す
            return "redirect:templates/admin/category/add";
        }

        return "redirect:templates/admin/category/add/confirm";
    }

    // キャンセルボタンが押下されメニュー画面が表示
    @GetMapping("/admin/category/add")
    public String menuPage(@PathVariable Integer id, Model model) {
        return "/admin"; // Thymeleafの画面名を返すだけ
    }

}
