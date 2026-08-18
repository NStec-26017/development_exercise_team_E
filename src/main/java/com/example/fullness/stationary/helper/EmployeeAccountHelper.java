package com.example.fullness.stationary.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.form.EmployeeAccountForm;

/**
 * UC009「担当者アカウント登録」のHelperクラス。
 *
 * <p>
 * Helperはアプリケーション層でのデータ変換やロジカルな処理を担当し、
 * Controller(業務単位)の補助機能として作成する。
 * </p>
 *
 * <p>
 * FormからEntityへの変換をこのクラスが担うことで、ドメイン層(Service)が
 * アプリケーション層のForm型をimportせずに済み、レイヤ間の依存関係を作らない。
 * </p>
 */
@Component
public class EmployeeAccountHelper {

    /**
     * BindingResultのエラー内容をメッセージのリストに変換する。
     * 
     * @param bindingResult 入力チェック結果
     * @return エラーメッセージのリスト
     */
    public List<String> toMessages(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }

    /**
     * 入力画面のFormを、登録用のEntityに変換する。
     *
     * @param employeeAccountForm 画面から入力された内容
     * @return 変換された社員アカウントEntity
     */
    public EmployeeAccount convert(EmployeeAccountForm employeeAccountForm) {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmployeeId(employeeAccountForm.getEmployeeId());
        // Form側の項目名(accountName)とEntity側の項目名(name)が異なるため、
        // ここで対応付けている
        employeeAccount.setName(employeeAccountForm.getAccountName());
        employeeAccount.setPassword(employeeAccountForm.getPassword());
        return employeeAccount;
    }

}
