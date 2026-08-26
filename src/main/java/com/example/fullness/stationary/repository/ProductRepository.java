package com.example.fullness.stationary.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.fullness.stationary.entity.Product;

/*
Repositoryはproductとproduct_stockをそれぞれつくらなきゃいけない
*/

/**
 * 商品テーブル({@link Product})に対するデータアクセスを提供するRepository。
 */
@Mapper
@Repository
public interface ProductRepository {

        // 商品情報一件取得(修正ボタンを押下して商品修正(入力)画面を開くとき)
        Product selectById(Integer id);

        // 商品情報を更新（修正完了時にDBに保存するとき）
        void updateProduct(Product product);

        // 検索画面
        List<Product> selectAll(
                        @Param("offset") int offset);

        List<Product> findByCategoryId(
                        @Param("categoryId") Integer categoryId,
                        @Param("offset") int offset);

        int countAll();

        int countByCategoryId(
                        @Param("categoryId") Integer categoryId);

        Product findById(@Param("id") Integer id);

        void updateDeleteFlag(@Param("id") Integer id);
}
