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
     * アカウントが未作成の社員を社員ID順に取得する。
     *
     * BP003「担当者アカウント登録(入力)」画面の社員名の選択肢に表示するために利用する。
     * 既にアカウントが作成済みの社員は選択肢に含めない。
     */
    List<Employee> selectAllWithoutAccount();

    /**
     * 社員IDで社員情報を取得する。
     * 
     * BP004「確認」画面への社員名の表示、および登録時の社員の実在確認で利用する。
     * 
     */
    Employee findById(Integer id);

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
