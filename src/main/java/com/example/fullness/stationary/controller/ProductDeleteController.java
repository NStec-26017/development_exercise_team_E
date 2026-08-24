package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductDeleteController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /** BP006「商品検索画面」から BP007「商品削除（確認）画面」に遷移 */
    @GetMapping("/delete/{productId}")
    public String productDelete(@PathVariable Integer productId, Model model) {
        Product product = productService.findById(productId);
        if (product == null) {
            return "redirect:/admin/product";
        }

        ProductStock stock = productService.findStockByProductld(productId);
        if (stock == null) {
            stock = new ProductStock();
            stock.setQuantity(0);
        }

        List<ProductCategory> categories = productCategoryRepository.selectAll();
        ProductCategory productCategory = categories.stream()
                .filter(c -> c.getId().equals(product.getProductCategoryId()))
                .findFirst()
                .orElse(null);

        model.addAttribute("product", product);
        model.addAttribute("stock", stock);
        model.addAttribute("productCategory", productCategory);
        return "admin/product/delete_confirm";
    }

    // 完了ボタン押下時（delete_flag= 1）
    @PostMapping("/delete/{productId}")
    public String deletePush(@PathVariable Integer productId, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productId);
        if (product != null) {
            productService.logicalDelete(productId);
            redirectAttributes.addFlashAttribute("productName", product.getName());
        }
        return "redirect:/admin/product/delete/complete";
    }

    @GetMapping("/delete/complete")
    public String deleteComplete() {
        return "admin/product/delete_complete";
    }
}
