package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

    @Test
    public void selectAllTest() {
        List<EmployeeAccount> actual = employeeAccountRepository.selectAll();

        Assertions.assertNotNull(actual);
        Assertions.assertFalse(actual.isEmpty());
        assertEquals(3, actual.size());

        EmployeeAccount employeeAccount = actual.get(0);
        Assertions.assertEquals(1, employeeAccount.getId());
        Assertions.assertEquals(101, employeeAccount.getEmployeeId());
        Assertions.assertEquals("fullness", employeeAccount.getName());
        Assertions.assertEquals("$2a$12$KYP1pth4anLhFgsF2dCp4eD/DOhhpXflfE9FDsW5WGx6PNivOB00a",
                employeeAccount.getPassword());
    }

    @Test
    public void selectByNameTest() {

        String name = "fullness";

        EmployeeAccount actual = employeeAccountRepository.selectByName(name);
        Assertions.assertNotNull(actual);

        Integer expectedId = 1;
        Integer expectedEmployeeId = 101;
        String expectedName = "fullness";
        String expectedPassword = "$2a$12$KYP1pth4anLhFgsF2dCp4eD/DOhhpXflfE9FDsW5WGx6PNivOB00a";

        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(expectedEmployeeId, actual.getEmployeeId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedPassword, actual.getPassword());
    }
}
