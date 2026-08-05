package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.fullness.stationary.service.EmployeeRegisterService;
import com.example.fullness.stationary.validator.EmployeeRegisterValidator;

import lombok.RequiredArgsConstructor;

/**
 * BP003【担当者アカウント登録】の画面遷移を担当するController。
 * 
 */
@Controller
@RequestMapping("/admin/account/form")
@SessionAttributes(names = { "EmployeeRegisterForm" })
@RequiredArgsConstructor
public class EmployeeRegisterController {

    private final EmployeeRegisterService employeeRegisterService;
    private final EmployeeRegisterValidator employeeRegisterValidator;

    // @InitBinder("EmployeeRegisterForm")
    // public void initBinder(WebDataBinder binder) {
    // binder.addValidators(employeeRegisterValidator);
    // }
}
