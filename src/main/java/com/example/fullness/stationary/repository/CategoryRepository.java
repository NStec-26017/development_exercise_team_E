package com.example.fullness.stationary.repository;

import java.util.List;

import com.example.fullness.stationary.entity.ProductCategory;

public interface CategoryRepository {

    List<ProductCategory> selectAll();

    // ID検索(主キー検索)
    ProductCategory selectById(int id);

    // レコード作成
    void insert(ProductCategory productcategory);

}
