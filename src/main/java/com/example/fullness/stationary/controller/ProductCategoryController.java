package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.fullness.stationary.entity.ProductCategory;
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

    /**
     * Form初期化
     * リクエストハンドラメソッド実行前に自動呼び出し
     */
    @ModelAttribute("Form")
    public CategoryForm setUpForm() {
        return new CategoryForm();
    }

    // 画面BP019商品カテゴリ登録(入力)画面を表示する(Get)
    @GetMapping("admin/category/add")
    public String showPage(HttpSession session, Model model) {
        CategoryForm form = (CategoryForm) session.getAttribute("sessioncategoryform");
        if (form == null) {
            form = new CategoryForm();
        }
        model.addAttribute("form", form);
        return "admin/category/form"; // Thymeleafの画面名を返すだけ
    }

    // BP019商品カテゴリ登録(入力)画面→BP020商品カテゴリ登録(確認)画面に遷移
    @PostMapping("/admin/category/add")
    public String changepage(CategoryForm form, HttpSession session, Model model) {
        // 引数に CategoryForm を定義しておくだけで、
        // 確認画面から送られてきたデータが自動的に保持され、入力画面に引き継がれる。
        session.setAttribute("sessioncategoryform", form);
        // 入力画面のHTMLテンプレート名を返す

        return "redirect:/admin/category/add/confirm";
        // ↑↑ここはURLがadd→confirmに変わるからresirectでURLを指定する↑↑

    }

    // BP020商品カテゴリ登録(確認)画面を表示
    @GetMapping("admin/category/add/confirm")
    public String page(HttpSession session, Model model) {
        model.addAttribute("form", session.getAttribute("sessioncategoryform"));
        // 引数に CategoryForm を定義しておくだけで、
        // 確認画面から送られてきたデータが自動的に保持され、入力画面に引き継がれる。

        // // 【バリデーション】入力チェックに引っかかった場合
        // if (result.hasErrors()) {
        // // エラー情報を持ったまま、もう一度「入力画面」に戻す
        // return "redirect:admin/category/form";
        // // ↑↑ここでは表示したいhtmlの場所を指定する↑↑
        // }

        return "admin/category/confirm";
    }

    // 画面BP020→画面BP021に遷移
    @PostMapping("/admin/category/add/confirm")
    // データを登録する、セッション情報を消す(自分が登録したキーだけ)
    public String nextgpage(
            @RequestParam(name = "action", required = false) String action,
            HttpSession session,
            Model model) {

        // if ("back".equals(action)) {
        // session.setAttribute("sessioncategoryform", form);
        // return "redirect:/admin/category/add";
        // }

        CategoryForm categoryForm = (CategoryForm) session.getAttribute("sessioncategoryform");
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(categoryForm.getName());
        productcategoryservice.registerCategory(productCategory);

        return "redirect:/admin/category/add/complete";
    }

    // 画面BP021商品カテゴリ登録(完了)画面を表示
    @GetMapping("/admin/category/add/complete")
    public String lastPage(HttpSession session, Model model) {
        CategoryForm categoryForm = (CategoryForm) session.getAttribute("sessioncategoryform");
        model.addAttribute("categoryName", categoryForm.getName());

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
