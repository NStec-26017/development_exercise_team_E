package com.example.fullness.stationary.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 社員(employee)テーブル用Entityクラス。
 *
 */
@Data
public class Employee implements Serializable {

    /** 社員ID。 */
    private Integer id;

    /** 部署ID(外部キー)。 */
    private Integer departmentId;

    /** 社員名。 */
    private String name;

    /** 社員名カナ。 */
    private String nameKana;

    /** 結合で取得したdepartmentの部署情報。 */
    private Department department;
}
