package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.fullness.stationary.form.CategoryForm;
import com.example.fullness.stationary.service.ProductCategoryService;

import jakarta.servlet.http.HttpSession;

/**
 * BP019【商品カテゴリ登録入力画面】用 コントローラー<br>
 * URL: admin/category/form
 *
 * @author Fullness, Inc.
 *
 */

@Controller
@SessionAttributes("CategoryForm")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productcategoryservice;

    // 画面BP019商品カテゴリ登録(入力)画面を表示する(Get)
    @GetMapping("admin/category/add")
    public String showPage(Model model) {
        return "admin/category/form"; // Thymeleafの画面名を返すだけ
    }

    // BP019商品カテゴリ登録(入力)画面→BP020商品カテゴリ登録(確認)画面に遷移
    @PostMapping("/admin/category/add")
    public String back(CategoryForm form, HttpSession session, Model model) {
        // 引数に CategoryForm を定義しておくだけで、
        // 確認画面から送られてきたデータが自動的に保持され、入力画面に引き継がれる。
        session.setAttribute("sessioncategoryform", form);
        // 入力画面のHTMLテンプレート名を返す

        return "redirect:/admin/category/add/confirm";
        // ↑↑ここはURLがadd→confirmに変わるからresirectでURLを指定する↑↑

    }

    // 画面BP019商品カテゴリ登録(入力)画面→画面BP021商品カテゴリ登録(完了)画面に遷移
    @GetMapping("admin/category/add/confirm")
    public String page(HttpSession session, Model model) {
        model.addAttribute("form", session.getAttribute("sessioncategoryform"));
        // 引数に CategoryForm を定義しておくだけで、
        // 確認画面から送られてきたデータが自動的に保持され、入力画面に引き継がれる。

        // 入力画面のHTMLテンプレート名を返す

        // // 【バリデーション】入力チェックに引っかかった場合
        // if (result.hasErrors()) {
        // // エラー情報を持ったまま、もう一度「入力画面」に戻す
        // return "redirect:admin/category/form";
        // // ↑↑ここでは表示したいhtmlの場所を指定する↑↑
        // }

        return "redirect:/admin/category/add/complate";
    }

    // 画面BP021商品カテゴリ登録(完了)画面を表示
    @GetMapping("/admin/category/add/complete")
    public String nextPage(CategoryForm form, Model model) {
        return "admin/category/complete"; // Thymeleafの画面名を返すだけ
    }

    // 確認画面→入力画面に戻る
    // @PostMapping("/admin/category/add")
    // public String back(CategoryForm form, HttpSession session, Model model) {
    // // 引数に CategoryForm を定義しておくだけで、
    // // 確認画面から送られてきたデータが自動的に保持され、入力画面に引き継がれる。
    // session.setAttribute("sessioncategoryform", form);
    // // 入力画面のHTMLテンプレート名を返す

    // return "redirect:/admin/category/add/confirm";
    // }
}
