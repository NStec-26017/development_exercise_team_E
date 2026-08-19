package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * 社員アカウントに対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeAccountRepository {

    /**
     * 指定されたアカウント名が既に登録されているかを確認する。
     */
    Boolean existsByName(String name);

    /**
     * 社員アカウントを新規登録する。
     *
     */
    Boolean create(EmployeeAccount employeeAccount);

}