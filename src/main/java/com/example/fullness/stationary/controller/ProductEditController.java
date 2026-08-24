package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineGroupConversionForCascadedReturnValue;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;
import com.example.fullness.stationary.service.ProductCategoryService;
import com.example.fullness.stationary.service.ProductEditService;

/*
 * UC012「商品修正」Controller
 */

@Controller
@RequestMapping("/admin/product/edit")
@SessionAttributes("ProductEditForm")
public class ProductEditController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductEditService productEditService;

    // BP009 商品修正（入力）画面の表示

    @GetMapping("/{productId}")
    public String editForm(@PathVariable Integer productId, Model model) {

        // ①DBを検索してデータを取得 Service→Mapper
        Product product = productEditService.findById(productId);

        // ②Viewに表示するデータを渡す Formの形にする
        ProductEditForm form = new ProductEditForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        // form.setStock(product.getStock());
        form.setCategoryId(product.getProductCategoryId());
        // form.setDeleteFlag(product.getDeleteFlag());

        List<ProductCategory> categories = productCategoryService.findAll();

        model.addAttribute("ProductEditForm", form);
        model.addAttribute("categories", categories);

        return "admin/product/edit_form";
    }

    // @PostMapping("/confirm")
    // public String

    // メソッド5つ用意する

}