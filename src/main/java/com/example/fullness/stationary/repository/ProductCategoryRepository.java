package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
@Repository
public interface ProductCategoryRepository {
    List<ProductCategory> selectAll();

    /**
     * uc010
     * 商品カテゴリを全件取得する
     * 
     */
    List<ProductCategory> selectAllOrderByIdTen();

    /**
     * uc010
     * 商品カテゴリIDを指定して商品カテゴリを１件取得する
     * 
     * @param id
     * @return
     */
    ProductCategory selectByIdTen(Integer id);

}
