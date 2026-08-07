package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * 社員アカウントに対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeAccountRepository {
    List<EmployeeAccount> selectAll();

    EmployeeAccount selectByName(String name);

    /**
     * アカウント名でアカウント情報を取得する。
     *
     * <p>
     * UC017「担当者ログイン」の認証処理で利用する。
     * </p>
     *
     * @param name アカウント名
     * @return アカウント情報(社員情報を結合して取得)。該当が無い場合はnull
     */
    EmployeeAccount findByName(String name);

    /**
     * 指定されたアカウント名が既に登録されているかを確認する。
     *
     * <p>
     * UC009「担当者アカウント登録」の重複チェックで利用する。
     * </p>
     *
     * @param name アカウント名
     * @return 登録済みの場合はtrue
     */
    Boolean existsByName(String name);

    /**
     * 社員アカウントを新規登録する。
     *
     * @param employeeAccount 登録する社員アカウント
     * @return 登録に成功した場合はtrue
     */
    Boolean create(EmployeeAccount employeeAccount);
}
