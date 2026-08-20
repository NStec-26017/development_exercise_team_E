package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

// カテゴリ名に対するデータアクセスを提供するRepositpry

@Mapper
@Repository
public interface ProductCategoryRepository {

    List<ProductCategory> selectAll();

    // カテゴリ名で商品カテゴリの情報を取得する
    ProductCategory selectByName(String name);

    // 指定されたカテゴリ名が既に登録されているか(重複しているか)を確認

    // @param name 商品カテゴリ名
    // @return 登録済みの場合はtrue

    Boolean existByName(String name);

    // 商品カテゴリを新規登録
    // @param name 登録するカテゴリ名
    // @return 登録に成功した場合はtrue

    // ↓↓XMLに書いたからいらない↓↓
    // @Insert("INSERT INTO categories (name) VALUES (#{name})")
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean create(ProductCategory productcategory);

    // データベースの自動採番の資料
    // データベース側が自動的に採番される仕組みが入っているかを確認(テーブルに)
}
