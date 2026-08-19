package com.example.fullness.stationary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeLoginServiceTest {

    @Mock
    private EmployeeAccountRepository employeeAccountRepository;

    @InjectMocks
    private EmployeeLoginService employeeLoginService;

    @Test
    public void loadUserByUsername() {
        EmployeeAccount account = new EmployeeAccount();
        account.setName("fullness");
        account.setPassword("fullness");

        when(employeeAccountRepository.selectByName("fullness")).thenReturn(account);

        UserDetails userDetails = employeeLoginService.loadUserByUsername("fullness");

        assertThat(userDetails.getUsername()).isEqualTo("fullness");
        assertThat(userDetails.getPassword()).isEqualTo("fullness");
        assertThat(userDetails.getAuthorities())
                .extracting("authority")
                .containsExactly("ROLE_USER");
    }

    @Test
    public void loadUserByUsernameTest() {
        when(employeeAccountRepository.selectByName("mamezou")).thenReturn(null);

        assertThatThrownBy(() -> employeeLoginService.loadUserByUsername("mamezou"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("アカウントが見つかりません");
    }
}
