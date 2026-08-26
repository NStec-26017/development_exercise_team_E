package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fullness.stationary.entity.ProductStock;

@Repository
@Mapper
public interface ProductStockRepository {

    // 在庫情報を一件取得
    ProductStock selectByProductId(@Param("productId") Integer productId);

    // 在庫情報を更新（修正完了時にDBに保存）
    void updateStock(ProductStock productStock);
    /**
     * uc010
     * 商品在庫を１件取得する
     * 
     * @param productStock
     * @return
     */
    int insertProductStockTen(ProductStock productStock);

    ProductStock findById(@Param("productId") Integer productId);

    List<Map<String, Object>> selectProductStockJoin();
}
