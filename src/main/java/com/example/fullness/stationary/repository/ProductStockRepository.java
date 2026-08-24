package com.example.fullness.stationary.repository;

import com.example.fullness.stationary.entity.ProductStock;

public interface ProductStockRepository {

    /**
     * uc010
     * 商品在庫を１件取得する
     * 
     * @param productStock
     * @return
     */
    Boolean createTen(ProductStock productStock);

    /**
     * uc010
     * 商品IDを指定して商品在庫を１件取得する
     * 
     * @param productId
     * @return
     */
    ProductStock selectByProductStockIdTen(Integer productId);

}
