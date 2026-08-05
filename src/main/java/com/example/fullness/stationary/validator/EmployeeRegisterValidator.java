package com.example.fullness.stationary.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.fullness.stationary.form.EmployeeRegisterForm;

/**
 * UC009「担当者アカウント登録」のValidator
 */
@Component
public class EmployeeRegisterValidator implements Validator {

    /** アカウント名・パスワードの最小文字数 */
    private static final int MIN_LENGTH = 5;

    /** アカウント名・パスワードの最大文字数 */
    private static final int MAX_LENGTH = 20;

    /** アカウント名・パスワードで許可する文字種(半角英数字)を表す正規表現。 */
    private static final Pattern HALF_WIDTH_ALNUM = Pattern.compile("^[A-Za-z0-9]+$");

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeRegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeRegisterForm request = (EmployeeRegisterForm) target;

        // 社員の選択チェック
        if (request.getEmployeeId() == null) {
            errors.rejectValue("employeeId", "",
                    "社員名を選択してください");
        }

        // アカウント名のチェック
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName",
                "", "アカウント名を入力してください");
        // アカウントが入力された場合、文字数・文字種をチェックする
        if (!errors.hasFieldErrors("accountName")) {
            checkLengthAndPattern(errors, "accountName", request.getAccountName(), "アカウント名");
        }

        // パスワードのチェック
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "", "パスワードを入力してください");
        if (!errors.hasFieldErrors("password")) {
            checkLengthAndPattern(errors, "password", request.getPassword(), "パスワード");
        }
    }

    /**
     * 文字数(5〜20文字)と文字種(半角英数字)をチェックする。
     * 
     * @param errors
     * @param fieldName
     * @param value
     * @param displayName
     */
    private void checkLengthAndPattern(Errors errors, String fieldName, String value, String displayName) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            errors.rejectValue(fieldName, fieldName + ".length",
                    displayName + "は5〜20文字で入力してください");
        } else if (!HALF_WIDTH_ALNUM.matcher(value).matches()) {
            errors.rejectValue(fieldName, fieldName + ".pattern",
                    displayName + "は半角英数字で入力してください");
        }
    }
}