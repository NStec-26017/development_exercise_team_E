package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeAccountServiceImplTest {

    @InjectMocks
    EmployeeAccountServiceImpl employeeAccountServiceImpl;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmployeeAccountRepository employeeAccountRepository;

    @Test
    public void findEmployeesWithoutAccountTest_OK() {
        Employee employee = new Employee();
        employee.setId(104);
        List<Employee> expected = Arrays.asList(employee);

        when(employeeRepository.selectAllWithoutAccount()).thenReturn(expected);

        List<Employee> actual = employeeAccountServiceImpl.findEmployeesWithoutAccount();

        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest_OK() {
        Employee expected = new Employee();
        expected.setId(104);

        when(employeeRepository.findById(104)).thenReturn(expected);

        Employee actual = employeeAccountServiceImpl.findById(104);

        assertEquals(expected, actual);
    }

    @Test
    public void createTest_OK() {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmployeeId(104);
        employeeAccount.setName("mamezou");
        employeeAccount.setPassword("mamezou");

        when(employeeAccountRepository.existsByName("mamezou")).thenReturn(false);

        employeeAccountServiceImpl.create(employeeAccount);
    }
}
