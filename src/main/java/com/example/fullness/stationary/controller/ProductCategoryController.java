package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.CategoryForm;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductCategoryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
    ProductCategoryService productCategoryService;

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
    // public String showPage(@ModelAttribute("form") CategoryForm form, HttpSession
    // session, Model model) {
    public String showPage(HttpSession session, Model model) {
        CategoryForm form = new CategoryForm();
        CategoryForm sessionForm = (CategoryForm) session.getAttribute("form");
        if (sessionForm != null) {
            form.setName(sessionForm.getName());
        }
        model.addAttribute("form", form);
        return "admin/category/form";
    }

    // BP019商品カテゴリ登録(入力)画面→BP020商品カテゴリ登録(確認)画面に遷移
    @PostMapping("/admin/category/add")
    public String changepage(@Valid @ModelAttribute("form") CategoryForm form, BindingResult bindingResult,
            HttpSession session, Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));

            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("form", form);

            // 入力画面にリダイレクト
            return "redirect:/admin/category/add";
        }

        // 例外・カテゴリ名重複
        // 小文字のインスタンス変数（productCategoryService）を使い、メソッド名を existByName に修正

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(form.getName());

        if (productCategoryService.duplicateCheck(productCategory)) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("入力されたカテゴリ名は既に登録されています。");
            // model.addAttribute("errorMessages",errorMessages);

            // リダイレクトする場合は↓↓でかく
            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
            return "redirect:/admin/category/add";
        }

        return "admin/category/confirm";
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
            @Valid @ModelAttribute("form") CategoryForm form,
            BindingResult bindingResult,
            HttpSession session,
            SessionStatus sessionStatus,
            Model model) {

        if ("back".equals(action)) {
            session.setAttribute("form", form);
            return "redirect:/admin/category/add";
        }

        if (bindingResult.hasErrors() || !StringUtils.hasText(form.getName())) {
            List<String> errorMessages = new ArrayList<>();
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
            } else {
                errorMessages.add("カテゴリー名を入力してください");
            }
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("form", form);
            session.setAttribute("form", form);
            return "admin/category/form";
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(form.getName());
        productCategoryService.registerCategory(productCategory);

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
