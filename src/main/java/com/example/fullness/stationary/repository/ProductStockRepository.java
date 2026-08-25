package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductStockRepository {

    // product_stock テーブル
    ProductStock selectByProductId(@Param("productId") Integer productId);

    // 在庫情報を更新（修正完了時にDBに保存）
    void updateStock(ProductStock productStock);
}
