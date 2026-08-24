package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductStockRepository {

    // product_stock テーブル
    ProductStock selectByProductId(Integer productId);
}
