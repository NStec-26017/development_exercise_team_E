package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

/*
Repositoryはproductとproduct_stockをそれぞれつくらなきゃいけない
*/

@Mapper
public interface ProductRepository {

        // 修正ボタンが押下されて商品修正(入力)画面を開くとき：IDで商品を1件だけ特定して持ってくる
        Product selectById(Integer id);

        // カテゴリをプルダウンに表示するために、すべてのカテゴリーマスタを取得する
        List<ProductCategory> selectAllCategories();

        // // 修正して「完了」ボタンを押したとき：商品データを上書き更新する
        // void editProduct(Product product);

        // //
        // 確認画面へ進むとき：選択されたカテゴリIDが本当にマスタに存在するかチェックする。存在しないIDで書き換えられた場合のため。↓@paramをつけるべき？
        // boolean existsCategory(@Param("categoryId") Integer categoryId);

}
