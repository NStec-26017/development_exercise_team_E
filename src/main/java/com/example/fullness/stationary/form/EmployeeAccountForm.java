package com.example.fullness.stationary.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * BP003「担当者アカウント登録(入力)」画面のForm。
 */
@Data
public class EmployeeAccountForm implements Serializable {

    /**
     * 優先順位をつけたかったのですが、バリデーションが機能しなくなってしまったので後回しにします。
     */
    // public static interface GroupA {
    // };

    // public static interface GroupB {
    // };

    /** 社員ID(社員名の選択肢で選ばれた値)。 */
    @NotNull(message = "社員名を選択してください")
    private Integer employeeId;

    /** アカウント名。 */
    @NotBlank(message = "アカウント名を入力してください")
    @Size(min = 5, max = 20, message = "アカウント名は5～20文字で入力してください")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "アカウント名は半角英数字で入力してください")
    private String accountName;

    /** パスワード(平文)。 */
    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 5, max = 20, message = "パスワードは5～20文字で入力してください")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "パスワードは半角英数字で入力してください")
    private String password;

    /** BP004「確認」画面に表示する社員名 */
    private String employeeName;
}
