package com.example.fullness.stationary.controller;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductForm;
import com.example.fullness.stationary.form.ProductStockForm;
import com.example.fullness.stationary.service.ProductRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/product")
@SessionAttributes({ "productForm", "productStockForm" })
public class ProductRegisterController {

    @Autowired
    private ProductRegisterService productRegisterService;

    @ModelAttribute("productForm")
    public ProductForm setUpProductForm() {
        return new ProductForm();
    }

    @ModelAttribute("productStockForm")
    public ProductStockForm setUpProductStockForm() {
        return new ProductStockForm();
    }

    /**
     * STEP 1: 新商品登録（入力）画面の表示
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {

        // セッションにデータがない場合（新規アクセス時）のみ、新しいインスタンスを生成
        if (!model.containsAttribute("productForm")) {
            model.addAttribute("productForm", new ProductForm());
        }
        if (!model.containsAttribute("productStockForm")) {
            model.addAttribute("productStockForm", new ProductStockForm());
        }

        model.addAttribute("categoryList",
                productRegisterService.selectAllCategoryTen());
        return "admin/product/add_form";
    }

    /**
     * STEP 2: 入力内容の検証（PRGパターン）
     */
    @PostMapping("/add")
    public String processAdd(
            @Valid @ModelAttribute("productForm") ProductForm productForm,
            BindingResult productFormResult,
            @Valid @ModelAttribute("productStockForm") ProductStockForm productStockForm,
            BindingResult productStockFormResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // バリデーションエラーがある場合は入力画面に戻る
        if (productFormResult.hasErrors() || productStockFormResult.hasErrors()) {
            model.addAttribute("categoryList",
                    productRegisterService.selectAllCategoryTen());
            return "admin/product/add_form";
        }

        // カテゴリ名を取得してFlash Attributeに保存
        ProductCategory category = productRegisterService
                .selectByIdTen(productForm.getProductCategoryId());
        redirectAttributes.addFlashAttribute("categoryName", category.getName());

        return "redirect:/admin/product/confirm";
    }

    /**
     * STEP 3: 新商品登録（確認）画面の表示
     */
    @GetMapping("/confirm")
    public String showConfirm(
            @ModelAttribute("productForm") ProductForm productForm,
            @ModelAttribute("productStockForm") ProductStockForm productStockForm,
            Model model) {

        // カテゴリ名がない場合は再取得
        if (!model.containsAttribute("categoryName")) {
            ProductCategory category = productRegisterService
                    .selectByIdTen(productForm.getProductCategoryId());
            model.addAttribute("categoryName", category.getName());
        }

        return "admin/product/add_confirm";
    }

    /**
     * STEP 4: 登録処理（PRGパターン）
     */
    @PostMapping("/complete")
    public String processComplete(
            @RequestParam(required = false) String action,
            @ModelAttribute("productForm") ProductForm productForm,
            @ModelAttribute("productStockForm") ProductStockForm productStockForm,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes) {

        // 「戻る」ボタンが押された場合
        if ("back".equals(action)) {
            return "redirect:/admin/product/add";
        }

        // エンティティに変換
        Product product = toProduct(productForm);
        ProductStock productStock = toProductStock(productStockForm);

        productRegisterService.insertProduct(product, productStock);

        // 完了画面に表示する商品名をFlash Attributeで渡す
        redirectAttributes.addFlashAttribute("registerProductName",
                productForm.getName());

        // セッションをクリア
        sessionStatus.setComplete();

        return "redirect:/admin/product/complete";
    }

    /**
     * STEP 5: 新商品登録（完了）画面の表示
     */
    @GetMapping("/complete")
    public String showComplete() {
        return "admin/product/add_complete";
    }

    /**
     * ProductForm → Product エンティティ変換
     */
    private Product toProduct(ProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setProductCategoryId(form.getProductCategoryId());
        product.setImagePath(form.getImagePath());
        product.setDeleteFlag(0);
        return product;
    }

    /**
     * ProductStockForm → ProductStock エンティティ変換
     */
    private ProductStock toProductStock(ProductStockForm form) {
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(form.getQuantity());
        return productStock;
    }
}