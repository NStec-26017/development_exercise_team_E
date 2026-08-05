package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BP001「メニュー画面」Controller
 */
@Controller
@RequestMapping("/admin")
public class MenuController {

    /**
     * メニュー画面の表示
     * 
     * @return メニュー画面(menu)
     */
    @GetMapping()
    public String showMenu() {
        return "menu";
    }
}
