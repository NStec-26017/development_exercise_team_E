package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
public interface ProductCategoryRepository {
    // カテゴリをプルダウンに表示するために、すべてのカテゴリーマスタを取得する
    List<ProductCategory> selectAllCategories();

    // IDでカテゴリを1件だけ特定して持ってくる（確認画面でカテゴリ名を表示するとき）
    ProductCategory selectCategoryById(@Param("categoryId") Integer categoryId);
}
