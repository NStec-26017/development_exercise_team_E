package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.exception.BusinessException;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.repository.EmployeeRepository;

import java.util.List;
import java.util.Locale;

/**
 * {@link EmployeeAccountService}の実装クラス。
 */
@Service
@Transactional(readOnly = true)
public class EmployeeAccountServiceImpl implements EmployeeAccountService {

    /** アカウント名重複時のメッセージキー。 */
    private static final String DUPLICATE_MESSAGE_KEY = "com.company.ecsite.service.EmployeeAccountService.duplicate";

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MessageSource messageSource;

    @Override
    public List<Employee> findEmployeesWithoutAccount() {
        return employeeRepository.selectAllWithoutAccount();
    }

    @Override
    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * 処理の流れ:
     * </p>
     * <ol>
     * <li>アカウント名が既に登録されていないか確認する</li>
     * <li>登録済みであればメッセージを取得して業務例外をスローする</li>
     * <li>パスワードをハッシュ値化して再設定する</li>
     * <li>社員アカウントを登録する</li>
     * </ol>
     */
    @Transactional
    @Override
    public void create(EmployeeAccount employeeAccount) {
        if (employeeAccountRepository.existsByName(employeeAccount.getName())) {
            String msg = messageSource.getMessage(DUPLICATE_MESSAGE_KEY, null, Locale.JAPAN);
            throw new BusinessException(msg);
        }
        String password = employeeAccount.getPassword();
        employeeAccount.setPassword(passwordEncoder.encode(password));
        employeeAccountRepository.create(employeeAccount);
    }
}
