package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Employee;

/**
 * 社員テーブルに対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeRepository {

    /**
     * アカウントが未作成の社員を社員ID順に取得する。
     *
     * <p>
     * BP003「担当者アカウント登録(入力)」画面の社員名の選択肢に表示するために利用する。
     * 仕様書の「データ取得仕様」に従い、既にアカウントが作成済みの社員は選択肢に含めない。
     * </p>
     *
     * @return アカウント未作成の社員のリスト
     */
    List<Employee> selectAllWithoutAccount();

    /**
     * 社員IDで社員情報を取得する。
     *
     * @param id 社員ID
     * @return 社員情報。該当が無い場合はnull
     */
    Employee findById(Integer id);
}
