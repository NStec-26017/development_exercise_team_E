package com.example.fullness.stationary.form;

import lombok.Data;

/**
 * BP003【アカウント登録(入力)】画面から送信される内容を保持するform
 */
@Data
public class EmployeeRegisterForm {

    /** 選択された社員のID */
    private int employeeId;

    /** 入力されたアカウント名 */
    private String accountName;

    /** 入力されたパスワード */
    private String password;
}
