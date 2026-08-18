package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BP001「メニュー画面」Controller
 */

@Controller
@RequestMapping("/admin")
public class MenuController {

    @GetMapping()
    public String showMenu() {

        return "admin/menu";
    }
}