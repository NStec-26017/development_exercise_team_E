package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Department;

/**
 * 部署テーブル({@link Department})に対するデータアクセスを提供するRepository。
 */
@Mapper
public interface DepartmentRepository {

    // 全権検索
    List<Department> selectAll();

    // ID検索（主キー検索）
    Department selectById(int id);

    // レコード作成
    int insert(Department department);

    // 更新
    int updateById(Department department);

    // 削除
    int deleteById(int id);
}
