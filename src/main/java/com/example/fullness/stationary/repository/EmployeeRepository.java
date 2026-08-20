package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Employee;

/**
 * 社員テーブル({@link Employee})に対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeRepository {

    /**
     * 全件取得
     * 
     * @return 全社員のリスト
     */
    List<Employee> selectAll();

    /**
     * pk で1件取得
     * 
     * @param id
     * @return 該当する社員
     */
    Employee selectById(Integer id);
}
