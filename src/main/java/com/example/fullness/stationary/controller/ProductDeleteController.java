package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductDeleteController {

    /** BP006「商品検索画面」から BP007「商品削除（確認）画面」に遷移 */
    @GetMapping("/delete/{productId}")
    public String productDelete(@PathVariable Integer productId, Model model) {
        return "admin/product/delete_confirm";
    }
}
