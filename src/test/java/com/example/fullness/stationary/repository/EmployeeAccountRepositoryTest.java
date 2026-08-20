package com.example.fullness.stationary.repository;

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
        Assertions.assertEquals("fullness", employeeAccount.getPassword());
    }

    @Test
    public void selectByNameTest() {

        String name = "fullness";

        EmployeeAccount actual = employeeAccountRepository.selectByName(name);
        Assertions.assertNotNull(actual);

        Integer expectedId = 1;
        Integer expectedEmployeeId = 101;
        String expectedName = "fullness";
        String expectedPassword = "fullness";

        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(expectedEmployeeId, actual.getEmployeeId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedPassword, actual.getPassword());
    }
}
