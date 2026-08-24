package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductStock;

@Mapper
@Repository
public interface ProductStockRepository {

    /**
     * uc010
     * 商品在庫を１件取得する
     * 
     * @param productStock
     * @return
     */
    int insertProductStockTen(ProductStock productStock);

    /**
     * uc010
     * 商品IDを指定して商品在庫を１件取得する
     * 
     * @param productId
     * @return
     */
    ProductStock selectByProductStockIdTen(Integer productId);

}
