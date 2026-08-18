package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// **
//  * BP020【商品カテゴリ登録確認画面】用 コントローラー<br>
//  * URL: /admin/category/add/confirm
//  *
//  * @author Fullness, Inc.
//  *
//  */
@Controller
public class ProductCategoryRegistCheckServlet {

    // 画面を表示する(Get)
    @GetMapping
    public String showPage(@PathVariable Integer id, Model model) {
        return "redirect:templates/admin/category/add/confirm"; // Thymeleafの画面名を返すだけ
    }

}
