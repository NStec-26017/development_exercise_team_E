package com.example.fullness.stationary.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductRegisterController {

    @GetMapping
    public String form() {
        return "admin/product/add_form";
    }
}
