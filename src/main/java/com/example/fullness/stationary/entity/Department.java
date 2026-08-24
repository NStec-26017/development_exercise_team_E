package com.example.fullness.stationary.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 部署(department)テーブル用Entityクラス。
 */
@Data
public class Department implements Serializable {

    /** 部署ID。 */
    private Integer id;

    /** 部署名。 */
    private String name;
}
