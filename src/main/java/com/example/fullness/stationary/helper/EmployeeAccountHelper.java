package com.example.fullness.stationary.helper;

import org.springframework.stereotype.Component;

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
     * 入力画面のFormを、登録用のEntityに変換する。
     *
     * @param employeeAccountForm 画面から入力された内容
     * @return 変換された社員アカウントEntity
     */
    public EmployeeAccount convert(EmployeeAccountForm employeeAccountForm) {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmployeeId(employeeAccountForm.getEmployeeId());
        employeeAccount.setName(employeeAccountForm.getAccountName());
        employeeAccount.setPassword(employeeAccountForm.getPassword());
        return employeeAccount;
    }
}
