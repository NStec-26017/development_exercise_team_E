package com.example.fullness.stationary.service;

import java.util.List;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * UC009「担当者アカウント登録」の業務機能を提供するServiceインターフェース。
 *
 * <p>
 * Serviceはインターフェース依存で設計・実装する。実装クラスは
 * {@link EmployeeAccountServiceImpl}。
 * </p>
 */
public interface EmployeeAccountService {

    /**
     * アカウントが未作成の社員を取得する。
     *
     * <p>
     * BP003「担当者アカウント登録(入力)」画面の社員名の選択肢に表示する。
     * </p>
     *
     * @return アカウント未作成の社員のリスト
     */
    List<Employee> findEmployeesWithoutAccount();

    /**
     * 社員IDで社員情報を取得する。
     *
     * <p>
     * BP004「確認」画面に社員名を表示するために利用する。
     * </p>
     *
     * @param employeeId 社員ID
     * @return 社員情報。該当が無い場合はnull
     */
    Employee findEmployeeById(Integer employeeId);

    /**
     * 社員アカウントを新規登録する。
     *
     * <p>
     * アカウント名の重複を確認したうえで、パスワードをハッシュ値化して登録する。
     * </p>
     * 
     * 
     */
    void create(EmployeeAccount employeeAccount);
}
