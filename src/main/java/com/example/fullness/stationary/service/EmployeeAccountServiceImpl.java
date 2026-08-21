package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.exception.BusinessException;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.repository.EmployeeRepository;

import java.util.List;

/**
 * {@link EmployeeAccountService}の実装クラス。
 */
@Service
@Transactional(readOnly = true)
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

    /** 社員テーブルにアクセスするRepository。 */
    @Autowired
    EmployeeRepository employeeRepository;

    /** 社員アカウントテーブルにアクセスするRepository。 */
    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    /** パスワードのハッシュ値化を行うPasswordEncoder(SecurityConfigでBean登録)。 */
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> findEmployeesWithoutAccount() {
        return employeeRepository.selectAllWithoutAccount();
    }

    @Override
    public Employee findById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Transactional
    @Override
    public void create(EmployeeAccount employeeAccount) {

        // アカウント名の重複確認。
        // データベースを参照しないと判定できないため、Formの入力チェックではなく
        // 業務ルールとしてServiceが担当する
        if (employeeAccountRepository.existsByName(employeeAccount.getName())) {
            // 業務ルールに違反したことをControllerへ通知する。
            // ControllerのExceptionHandlerがこの例外を受け取り、画面遷移を行う
            throw new BusinessException("このアカウント名は既に使用されています");
        }

        // パスワードは平文のままデータベースに保存してはならないため、
        // ハッシュ値化した値で上書きする。
        // ハッシュ値は元に戻せないため、ログイン時は入力値を同じ方式で
        // ハッシュ値化して照合する
        String password = employeeAccount.getPassword();
        employeeAccount.setPassword(passwordEncoder.encode(password));

        employeeAccountRepository.create(employeeAccount);
    }

}
