package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.EmployeeAccount;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeAccountRepositoryTest {

    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;

    // 存在するアカウント名で検索するとtrueが返る
    @Test
    public void existsByNameTest_OK() {
        String expectedName = "fullness";

        Boolean actual = employeeAccountRepository.existsByName(expectedName);

        Assertions.assertTrue(actual);
    }

    @Test
    public void createTest_OK() {
        EmployeeAccount employeeAccount = new EmployeeAccount();

        employeeAccount.setEmployeeId(104);
        employeeAccount.setName("mamezou");
        employeeAccount.setPassword("mamezou");

        Boolean actual = employeeAccountRepository.create(employeeAccount);

        assertTrue(actual);
    }

}
