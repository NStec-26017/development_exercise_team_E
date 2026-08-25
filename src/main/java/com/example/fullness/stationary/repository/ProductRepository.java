package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

/*
Repositoryはproductとproduct_stockをそれぞれつくらなきゃいけない
*/

@Mapper
public interface ProductRepository {

        /* UC009 */
        // IDで商品を1件だけ特定して持ってくる(修正ボタンを押下して商品修正(入力)画面を開くとき)
        Product selectById(Integer id);

        /* UC011 */
        // 商品情報を更新（修正完了時にDBに保存）
        void updateProduct(Product product);
}
