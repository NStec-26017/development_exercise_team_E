package com.example.fullness.stationary.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showError(HttpSession session, Model model) {

        String sessionMessage = (String) session.getAttribute("errorMessage");

        if (sessionMessage != null) {

            model.addAttribute("errorMessage", sessionMessage);

            session.removeAttribute("errorMessage");
        }

        return "error";
    }

}
