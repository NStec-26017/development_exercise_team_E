package com.example.fullness.stationary.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductForm;
import com.example.fullness.stationary.form.ProductStockForm;
import com.example.fullness.stationary.service.ProductRegisterService;

@Controller
@RequestMapping("/admin/product")
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
     * 新商品（入力）画面の表示
     * 
     * @return
     */
    @GetMapping("/add")
    public String form(Model model) {
        addFormViewAttributes(model);
        return "admin/product/add_form";
    }

    @PostMapping("/add_confirm")
    public String confirm(@Validated @ModelAttribute("productForm") ProductForm productForm,
            BindingResult bindingResult,
            @Validated @ModelAttribute("productStockForm") ProductStockForm productStockForm,
            BindingResult bindingResult2, Model model) {
        if (bindingResult.hasErrors() || bindingResult2.hasErrors()) {
            addFormViewAttributes(model);
            return "admin/product/add_form";
        }
        return "admin/product/add_confirm";
    }

    /**
     * confirmに直接いけないようにする
     * 
     * @return
     */
    @GetMapping("/add_confirm")
    public String confirmDirectAccess() {
        return "redirect:/admin/product/add_form";
    }

    @PostMapping("/back")
    public String back() {
        return "redirect:/admin/product/add_form";
    }

    private void addFormViewAttributes(Model model) {
        Object categoryList = productRegisterService.selectAllCategoryTen();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categories", categoryList);

        ProductForm productForm = (ProductForm) model.getAttribute("productForm");
        ProductStockForm productStockForm = (ProductStockForm) model.getAttribute("productStockForm");
        Map<String, Object> form = new HashMap<>();
        form.put("name", productForm.getName());
        form.put("price", productForm.getPrice());
        form.put("stock", productStockForm.getQuantity());
        form.put("categoryId", productForm.getProductCategoryId());
        form.put("imagePath", productForm.getImagePath());
        model.addAttribute("form", form);
    }

    @PostMapping("/complete")
    public String register(
            @ModelAttribute("productForm") ProductForm productForm,
            @ModelAttribute("productStockForm") ProductStockForm productStockForm,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes) {

        Product product = toProduct(productForm);
        ProductStock productStock = toProductStock(productStockForm);

        productRegisterService.insertProduct(product, productStock);

        redirectAttributes.addFlashAttribute("registerProductName",
                productForm.getName());
        sessionStatus.setComplete();

        return "redirect:/admin/product/add_complete";
    }

    @GetMapping("/complete")
    public String complete() {
        return "admin/product/add_complete";
    }

    /**
     * entityに変換
     * 
     * @param form
     * @return
     */
    private Product toProduct(ProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setProductCategoryId(form.getProductCategoryId());
        return product;
    }

    private ProductStock toProductStock(ProductStockForm form) {
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(form.getQuantity());
        return productStock;
    }

}
