package com.example.fullness.stationary.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 社員アカウント(employee_account)テーブル用Entityクラス。
 *
 */
@Data
public class EmployeeAccount implements Serializable {

    /** アカウントID。 */
    private Integer id;

    /** 社員ID(外部キー)。 */
    private Integer employeeId;

    /** アカウント名(ログイン時に使用する。社員名とは別物)。 */
    private String name;

    /** パスワード(ハッシュ値)。 */
    private String password;

    /** 結合で取得したemployeeの社員情報。 */
    private Employee employee;
}
