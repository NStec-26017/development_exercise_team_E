package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm implements Serializable {
    @NotBlank(message = "アカウントを入力してください")
    private String accountName;

    @NotBlank(message = "パスワードを入力してください")
    private String password;

}
