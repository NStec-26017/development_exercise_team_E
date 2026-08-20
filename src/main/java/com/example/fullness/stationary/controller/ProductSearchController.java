package com.example.fullness.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductSearchService;

/**
 * UC011「商品検索」の画面を遷移を担当するController
 * 
 */
@Controller
@RequestMapping("/admin/product")
@SessionAttributes("productAccountForm")
public class ProductSearchController {
    @Autowired
    ProductSearchService productSearchService;

    // @GetMapping("")
    // public String showProductSearch(
    // @RequestParam(required = false) Integer categoryId,
    // @RequestParam(defaultValue = "1") int page,
    // Model model) {
    // return "admin/product/search";
    // }

    @GetMapping("")
    public String showProductSearch(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "1") int page,
            Model model) {
        final int pageSize = 10;
        int offset = (Math.max(page, 1) - 1) * pageSize;

        List<Product> products;
        int totalCount;
        if (categoryId == null) {
            products = productSearchService.getProducts(offset);
            totalCount = productSearchService.countProducts();
        } else {
            products = productSearchService.getProductsByCategoryId(categoryId, offset);
            totalCount = productSearchService.countProductsByCategoryId(categoryId);
        }

        // // デバッグ出力
        // products.forEach(p -> System.out
        // .println("Product: id=" + p.getId() + ", name=" + p.getName() + ",
        // imagePath=" + p.getImagePath()));

        List<ProductCategory> categories = productSearchService.getProductCategories();

        int totalPages = (totalCount + pageSize - 1) / pageSize;

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/product/search";

    }

    /** BP006「商品検索画面」から BP007「商品削除（確認）画面」に遷移 */
    @GetMapping("/delete/{productId}")
    public String productDelete(@PathVariable Integer productId, Model model) {
        return "admin/product/delete_confirm";
    }

    /** BP006「商品検索画面」から BP009「商品修正（入力）画面」に遷移 */
    @GetMapping("/edit/{productId}")
    public String productEdit(@PathVariable Integer productId, Model model) {
        return "admin/product/edit_form";
    }

}
