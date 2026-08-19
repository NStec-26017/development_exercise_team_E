package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * 社員アカウント({@link EmployeeAccount})に対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeAccountRepository {
    /**
     * 全件取得
     * 
     * @return 全社員のリスト
     */
    List<EmployeeAccount> selectAll();

    EmployeeAccount selectByName(String name);

}
