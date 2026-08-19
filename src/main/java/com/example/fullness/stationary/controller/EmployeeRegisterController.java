package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.exception.BusinessException;
import com.example.fullness.stationary.form.EmployeeAccountForm;
import com.example.fullness.stationary.form.EmployeeAccountForm.GroupA;
import com.example.fullness.stationary.form.EmployeeAccountForm.GroupB;
import com.example.fullness.stationary.helper.EmployeeAccountHelper;
import com.example.fullness.stationary.service.EmployeeAccountService;

import jakarta.validation.GroupSequence;

import java.util.List;

/**
 * UC009「担当者アカウント登録」Controller
 */
@Controller
@RequestMapping("/admin/account")
@SessionAttributes("form")
public class EmployeeRegisterController {

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @Autowired
    private EmployeeAccountHelper employeeAccountHelper;

    // @GroupSequence({ GroupA.class, GroupB.class })
    // static interface GroupOrder {
    // };

    /**
     * Form初期化
     * リクエストハンドラメソッド実行前に自動呼び出し
     */
    @ModelAttribute("form")
    public EmployeeAccountForm setUpForm() {
        return new EmployeeAccountForm();
    }

    /**
     * アカウント未作成の社員リスト取得
     * 画面の選択肢に使用
     */
    @ModelAttribute("employees")
    public List<Employee> employees() {
        return employeeAccountService.findEmployeesWithoutAccount();
    }

    /**
     * BP003 入力画面表示
     */
    @GetMapping("/form")
    public String form() {
        return "admin/account/form";
    }

    /**
     * BP003→BP004 入力内容確認
     */
    @PostMapping("/form")
    public String confirm(@Validated @ModelAttribute("form") EmployeeAccountForm form,
            BindingResult bindingResult,
            Model model) {

        // 入力チェックでエラーがある場合の処理（例外シナリオ）
        if (bindingResult.hasErrors()) {
            // 1. Helperを使ってエラーメッセージのリストを作成し、モデルに格納する
            model.addAttribute("errorMessages", employeeAccountHelper.toMessages(bindingResult));

            // 2. 画面のセレクトボックス表示に必要な「社員リスト」を再取得してモデルに格納する
            // AI 意味が分かってないのですが、これがないとバリデーションが機能しないです。
            List<Employee> employees = employeeAccountService.findEmployeesWithoutAccount();
            model.addAttribute("employees", employees);

            return "admin/account/form";
        }
        return "admin/account/confirm";
    }

    /**
     * BP004 確認画面処理（戻る/登録）
     */
    @PostMapping("/confirm")
    public String process(@ModelAttribute("form") EmployeeAccountForm form,
            @RequestParam("action") String action,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus) {
        // 戻るボタン押下時
        if ("back".equals(action)) {
            return "admin/account/form";
        }

        // 登録処理
        EmployeeAccount employeeAccount = employeeAccountHelper.convert(form);
        employeeAccountService.create(employeeAccount);

        // PRGパターン：完了画面へリダイレクト
        redirectAttributes.addFlashAttribute("form", form);
        sessionStatus.setComplete();

        return "redirect:/admin/account/complete";
    }

    /**
     * BP005 完了画面表示
     */
    @GetMapping("/complete")
    public String complete() {
        return "admin/account/complete";
    }

    /**
     * 業務例外ハンドラ（アカウント名重複等）
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessages", List.of(e.getMessage()));
        return "redirect:/admin/account/form";
    }
}
