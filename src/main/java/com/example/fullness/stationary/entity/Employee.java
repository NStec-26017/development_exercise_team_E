package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 社員テーブルを示すentity
 */
@Data
public class Employee implements Serializable {
    private Integer id;
    private Integer departmentId;
    private String name;
    private String nameKana;
}
