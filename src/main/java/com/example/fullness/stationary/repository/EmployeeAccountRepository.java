package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * 社員アカウントに対するデータアクセスを提供するRepository。
 */
@Mapper
public interface EmployeeAccountRepository {
    /**
     * 全ての社員アカウントを取得する。
     *
     * @return 社員アカウントのリスト
     */
    List<EmployeeAccount> selectAll();

    /**
     * アカウント名で社員アカウントを取得する。
     *
     * @param name アカウント名
     * @return 社員アカウント(社員情報を結合して取得)。該当が無い場合はnull
     */
    EmployeeAccount selectByName(String name);

    /**
     * アカウント名で認証情報を取得する。
     *
     * <p>
     * UC017「担当者ログイン」の認証処理で利用する。
     * </p>
     *
     * @param name アカウント名
     * @return 社員アカウント。該当が無い場合はnull
     */
    EmployeeAccount findByName(String name);

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