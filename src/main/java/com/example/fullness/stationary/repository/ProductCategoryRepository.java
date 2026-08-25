package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

// カテゴリに対するデータアクセスを提供するRepositpry

@Mapper
@Repository
public interface ProductCategoryRepository {

    List<ProductCategory> selectAll();

    // @param name 商品カテゴリ名
    // @return 登録済みの場合はtrue

    Boolean existByName(String name);

    // 商品カテゴリを新規登録
    // @param name 登録するカテゴリ名
    // @return 登録に成功した場合はtrue

    Boolean create(ProductCategory productCategory);

}