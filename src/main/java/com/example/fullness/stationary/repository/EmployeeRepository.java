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
     * 全ての社員を取得する。
     *
     * @return 社員のリスト
     */
    List<Employee> selectAll();

    /**
     * アカウントが未作成の社員を社員ID順に取得する。
     *
     * <p>
     * BP003「担当者アカウント登録(入力)」画面の社員名の選択肢に表示するために利用する。
     * 仕様書のデータ取得仕様に従い、既にアカウントが作成済みの社員は選択肢に含めない。
     * </p>
     *
     * @return アカウント未作成の社員のリスト
     */
    List<Employee> selectAllWithoutAccount();

    /**
     * 社員IDで社員情報を取得する。
     *
     * <p>
     * BP004「確認」画面への社員名の表示、および登録時の社員の実在確認で利用する。
     * </p>
     *
     * @param id 社員ID
     * @return 社員情報(部署情報を結合して取得)。該当が無い場合はnull
     */
    Employee findById(Integer id);

}
