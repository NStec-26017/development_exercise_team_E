package com.example.fullness.stationary.controller;

<<<<<<< HEAD
=======
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
>>>>>>> main
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
<<<<<<< HEAD
    public String showMenu() {

        return "admin/menu";
    }
}
=======
    public String showMenu(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // ログインしていればtrue
        model.addAttribute("loggedIn", userDetails != null);

        if (userDetails != null) {
            // ログインユーザーの名前をセット
            model.addAttribute("loginEmployeeName", userDetails.getUsername());
        }

        return "admin/menu";
    }
}
>>>>>>> main
