package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 社員アカウントテーブルを示すentity
 */
@Data
public class EmployeeAccount implements Serializable {
    private int id;
    private int employeeId;
    private String name;
    private String password;
}
