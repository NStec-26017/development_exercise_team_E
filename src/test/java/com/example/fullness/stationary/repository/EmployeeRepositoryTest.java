package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.Employee;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void selectAllWithoutAccountTest_OK() {
        List<Employee> employees = employeeRepository.selectAllWithoutAccount();

        Assertions.assertNotNull(employees);
        Assertions.assertTrue(employees.size() > 0);
    }

    @Test
    public void findByIdTest() {

        Integer id = 104;

        Employee actual = employeeRepository.findById(id);
        Assertions.assertNotNull(actual);

        Integer expectedId = 104;
        Integer expectedDepartmentId = 1004;
        String expectedName = "豆田豆蔵";
        String expectedNameKana = "マメタマメゾウ";

        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(expectedDepartmentId, actual.getDepartmentId());
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedNameKana, actual.getNameKana());
    }
}
