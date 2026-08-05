package com.example.fullness.stationary.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.form.EmployeeRegisterForm;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

/**
 * BP003【担当者アカウント登録】の業務ロジックを担当するServiceクラス。
 * 
 */
@Service
@RequiredArgsConstructor
public class EmployeeRegisterService {

    private final EmployeeAccountRepository employeeAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 担当者アカウントを新規登録する。
     * 
     * @param request
     * @return
     */
    public Integer register(EmployeeRegisterForm request) {
        EmployeeAccount existingAccount = employeeAccountRepository.selectByName(request.getAccountName());
        if (existingAccount != null) {
            throw new IllegalArgumentException("このアカウント名は既に使用されています");
        }

        Employee employee = employeeRepository.selectById(request.getEmployeeId());
        if (employee == null) {
            throw new IllegalArgumentException("社員が見つかりません");
        }

        EmployeeAccount newAccount = new EmployeeAccount();
        newAccount.setEmployeeId(employee.getId());
        newAccount.setName(request.getAccountName());
        newAccount.setPassword(passwordEncoder.encode(request.getPassword()));

        employeeAccountRepository.insert(newAccount);
        return newAccount.getId();
    }
}
