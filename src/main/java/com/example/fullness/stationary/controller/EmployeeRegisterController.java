package com.example.fullness.stationary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.form.EmployeeRegisterForm;
import com.example.fullness.stationary.repository.EmployeeRepository;
import com.example.fullness.stationary.service.EmployeeRegisterService;
import com.example.fullness.stationary.validator.EmployeeRegisterValidator;

import groovy.transform.ASTTest;
import lombok.RequiredArgsConstructor;

/**
 * BP003【担当者アカウント登録】の画面遷移を担当するController。
 * 
 */
@Controller
@RequestMapping("/admin/account")
@SessionAttributes(names = { "employeeRegisterForm" })
@RequiredArgsConstructor
public class EmployeeRegisterController {

    private final EmployeeRegisterService employeeRegisterService;
    private final EmployeeRegisterValidator employeeRegisterValidator;
    private final EmployeeRepository employeeRepository;

    /**
     * バリデーションの設定
     * 
     * @param binder
     */
    @InitBinder("employeeRegisterForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(employeeRegisterValidator);
    }

    /**
     * 
     * @return 全社員のリスト
     */
    @ModelAttribute("employeeList")
    public List<Employee> employeeList() {
        return employeeRepository.selectAll();
    }

    /**
     * BP003「アカウント登録(入力)」画面を表示する。
     * 
     * @param form
     * @return
     */
    @GetMapping("/form")
    public String showInputForm(@ModelAttribute("employeeRegisterForm") EmployeeRegisterForm form) {
        return "account-form";
    }

    /**
     * BP004「アカウント登録(確認)」画面に遷移する
     * 
     * @param request
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute("employeeRegisterForm") EmployeeRegisterForm request,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account-form";
        }

        Employee employee = employeeRepository.selectById(request.getEmployeeId());
        if (employee != null) {
            model.addAttribute("employeeName", employee.getName());
        }
        return "account-confirm";
    }

    /**
     * BP004「アカウント登録(確認)」画面で「戻る」を押したとき
     * 
     * @return
     */
    @PostMapping("/back")
    public String back() {
        return "account-form";
    }

    /**
     * BP004「アカウント登録(確認)」画面で「キャンセル」を押したとき
     * 
     * @param sessionStatus
     * @return
     */
    @PostMapping("/cancel")
    public String cancel(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "menu";
    }

    /**
     * BP004「アカウント登録(確認)」画面で「登録」を押したとき
     * 
     * @param request
     * @param redirectAttributes
     * @param sessionStatus
     * @return
     */
    @PostMapping
    public String register(@ModelAttribute("employeeRegisterForm") EmployeeRegisterForm request,
            RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        Integer accountId = employeeRegisterService.register(request);
        redirectAttributes.addFlashAttribute("accountId", accountId);
        sessionStatus.setComplete();
        return "redirect:/admin/account/complete";
    }

    /**
     * BP005「アカウント登録(完了)」画面表示
     * 
     * @return
     */
    @GetMapping("/complete")
    public String showComplete() {
        return "account-complete";
    }
}
