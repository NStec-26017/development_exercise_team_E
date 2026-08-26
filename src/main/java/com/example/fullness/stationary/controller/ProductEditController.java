package com.example.fullness.stationary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.helper.ProductEditHelper;
import com.example.fullness.stationary.service.ProductEditService;
import com.example.fullness.stationary.service.ProductSearchService;

/**
 * UC012「商品修正」Controller
 */
@Controller
@RequestMapping("/admin/product/edit")
@SessionAttributes("form")
public class ProductEditController {

    @Autowired
    private ProductEditService productEditService;

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private ProductEditHelper productEditHelper;

    @ModelAttribute("categories")
    public List<ProductCategory> categories() {
        return productSearchService.getProductCategories();
    }

    /**
     * BP009 商品修正（入力）画面の表示
     */
    @GetMapping("/{productId}")
    public String editForm(@PathVariable("productId") Integer productId, Model model) {

        // 「戻る」ボタンで戻ってきたものか確認して、入力内容を保持
        if (model.containsAttribute("form")) {
            ProductEditForm sessionForm = (ProductEditForm) model.getAttribute("form");
            if (sessionForm != null && productId.equals(sessionForm.getId())) {
                return "admin/product/edit_form";
            }
        }

        // DBから最新情報を取得
        ProductEditForm form = productEditService.getEditForm(productId);
        model.addAttribute("form", form);
        return "admin/product/edit_form";
    }

    /**
     * BP009→BP010 商品修正（確認）画面への遷移
     */
    @PostMapping("/{productId}")
    public String postConfirmForm(@PathVariable("productId") Integer productId,
            @Validated @ModelAttribute("form") ProductEditForm form,
            BindingResult bindingResult,
            Model model) {

        // 入力チェックエラー時の処理
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessages", productEditHelper.toMessages(bindingResult));
            return "admin/product/edit_form";
        }
        return "redirect:/admin/product/edit/confirm";
    }

    /**
     * BP010 商品修正（確認）画面の表示
     */
    @GetMapping("/confirm")
    public String confirmForm(@ModelAttribute("form") ProductEditForm form, Model model) {
        ProductCategory category = productEditService.findCategoryById(form.getCategoryId());
        form.setCategoryName(category.getName());

        return "admin/product/edit_confirm";
    }

    /**
     * BP010 商品修正（確認）画面処理（戻る/更新）
     */
    @PostMapping(value = "/confirm", params = "action")
    public String postCompleteForm(
            @ModelAttribute("form") ProductEditForm form,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus) {

        Integer productId = form.getId();

        // 戻るボタン押下時
        if ("back".equals(action)) {
            return "redirect:/admin/product/edit/" + productId;
        }

        // Service で DB 更新処理を実行
        productEditService.updateProduct(productId, form);

        // PRGパターン：完了画面へリダイレクト
        redirectAttributes.addFlashAttribute("productName", form.getName());
        sessionStatus.setComplete();

        return "redirect:/admin/product/edit/complete";
    }

    /**
     * BP011 修正（完了）画面の表示
     */
    @GetMapping("/complete")
    public String completeForm() {
        return "admin/product/edit_complete";
    }
}