package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Comparator;
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
        List<Employee> actual = employeeRepository.selectAllWithoutAccount();

        assertNotNull(actual);
        assertEquals(2, actual.size());

        // IDでソートして順序を保証
        List<Employee> sortedActual = actual.stream()
                .sorted(Comparator.comparing(Employee::getId))
                .toList();

        Employee employee1 = sortedActual.get(0);
        assertEquals(104, employee1.getId());
        assertEquals("豆田豆蔵", employee1.getName());
        assertEquals("マメタマメゾウ", employee1.getNameKana());
        assertEquals(1004, employee1.getDepartmentId());

        Employee employee2 = sortedActual.get(1);
        assertEquals(105, employee2.getId());
        assertEquals("空田豆雄", employee2.getName());
        assertEquals("ソラタマメオ", employee2.getNameKana());
        assertEquals(1005, employee2.getDepartmentId());
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
