package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void findEmployeesWithoutAccountTest_OK() {
        Employee employee1 = new Employee();
        employee1.setId(104);
        employee1.setName("豆田豆蔵");

        Employee employee2 = new Employee();
        employee2.setId(105);
        employee2.setName("空田豆雄");

        List<Employee> expectedList = Arrays.asList(employee1, employee2);

        when(employeeRepository.selectAllWithoutAccount()).thenReturn(expectedList);

        List<Employee> actualList = employeeAccountServiceImpl.findEmployeesWithoutAccount();

        assertEquals(2, actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    public void findByIdTest_OK() {
        Employee expected = new Employee();
        expected.setId(104);
        expected.setName("豆田豆蔵");
        when(employeeRepository.findById(104)).thenReturn(expected);

        Employee actual = employeeAccountServiceImpl.findById(104);

        assertEquals(104, actual.getId());
        assertEquals("豆田豆蔵", actual.getName());
    }

    @Test
    public void createTest_OK() {
        // Setup
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmployeeId(104);
        employeeAccount.setName("mamezou");
        employeeAccount.setPassword("mamezou");

        when(employeeAccountRepository.existsByName("mamezou")).thenReturn(false);
        when(passwordEncoder.encode("mamezou")).thenReturn("encodedPassword123");

        employeeAccountServiceImpl.create(employeeAccount);

        verify(employeeAccountRepository, times(1)).existsByName("mamezou");
        verify(passwordEncoder, times(1)).encode("mamezou");
        verify(employeeAccountRepository, times(1)).create(any(EmployeeAccount.class));
    }

    @Test
    void createPasswordTest() {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setEmployeeId(104);
        employeeAccount.setName("mamezou");
        employeeAccount.setPassword("mamezou");

        String encodedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy";

        when(employeeAccountRepository.existsByName("mamezou")).thenReturn(false);
        when(passwordEncoder.encode("mamezou")).thenReturn(encodedPassword);

        employeeAccountServiceImpl.create(employeeAccount);

        ArgumentCaptor<EmployeeAccount> captor = ArgumentCaptor.forClass(EmployeeAccount.class);
        verify(employeeAccountRepository).create(captor.capture());

        EmployeeAccount captured = captor.getValue();

        assertEquals(encodedPassword, captured.getPassword());
        assertNotEquals("plainPassword", captured.getPassword());

    }
}
