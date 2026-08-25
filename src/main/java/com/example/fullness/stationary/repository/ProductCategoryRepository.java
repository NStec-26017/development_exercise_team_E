package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

/**
 * 商品カテゴリ({@link ProductCategory})に対するデータアクセスを提供するRepository。
 */
/**
 * 全件取得
 * 
 * @return 全商品カテゴリのリスト
 */

@Mapper
@Repository
public interface ProductCategoryRepository {
    // IDでカテゴリを1件だけ特定して持ってくる（確認画面でカテゴリ名を表示するとき）
    ProductCategory selectCategoryById(@Param("categoryId") Integer categoryId);

    // カテゴリ全件取得
    List<ProductCategory> selectAll();
}