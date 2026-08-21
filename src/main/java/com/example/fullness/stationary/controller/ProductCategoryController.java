package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
@SessionAttributes("form")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productcategoryservice;

    /**
     * Form初期化
     * リクエストハンドラメソッド実行前に自動呼び出し
     */
    @ModelAttribute("form")
    public CategoryForm setUpForm() {
        return new CategoryForm();
    }

    // 画面BP019商品カテゴリ登録(入力)画面を表示する(Get)
    @GetMapping("/admin/category/add")
    public String showPage(@ModelAttribute("form") CategoryForm form, HttpSession session, Model model) {
        CategoryForm sessionForm = (CategoryForm) session.getAttribute("form");
        if (sessionForm != null) {
            form.setName(sessionForm.getName());
        }
        model.addAttribute("form", form);
        return "admin/category/form";
    }

    // BP019商品カテゴリ登録(入力)画面→BP020商品カテゴリ登録(確認)画面に遷移
    @PostMapping("/admin/category/add")
    public String changepage(@ModelAttribute("form") CategoryForm form, HttpSession session, Model model) {
        session.setAttribute("form", form);
        model.addAttribute("form", form);
        return "redirect:/admin/category/add/confirm";
    }

    // BP020商品カテゴリ登録(確認)画面を表示
    @GetMapping("/admin/category/add/confirm")
    public String page(@ModelAttribute("form") CategoryForm form, HttpSession session, Model model) {
        CategoryForm sessionForm = (CategoryForm) session.getAttribute("form");
        if (sessionForm != null) {
            form.setName(sessionForm.getName());
        }
        model.addAttribute("form", form);
        return "admin/category/confirm";
    }

    // 画面BP020→画面BP021に遷移
    @PostMapping("/admin/category/add/confirm")
    // データを登録する、セッション情報を消す(自分が登録したキーだけ)
    public String nextgpage(
            @RequestParam(name = "action", required = false) String action,
            @ModelAttribute("form") CategoryForm form,
            HttpSession session,
            SessionStatus sessionStatus) {

        if ("back".equals(action)) {
            session.setAttribute("form", form);
            return "redirect:/admin/category/add";
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(form.getName());
        productcategoryservice.registerCategory(productCategory);

        session.setAttribute("registeredCategoryName", form.getName());
        sessionStatus.setComplete();
        session.removeAttribute("form");

        return "redirect:/admin/category/add/complete";
    }

    // 画面BP021商品カテゴリ登録(完了)画面を表示
    @GetMapping("/admin/category/add/complete")
    public String lastPage(HttpSession session, Model model) {
        String categoryName = (String) session.getAttribute("registeredCategoryName");
        if (categoryName == null) {
            categoryName = "";
        }
        model.addAttribute("categoryName", categoryName);

        return "admin/category/complete";
    }

}
