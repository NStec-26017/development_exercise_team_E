package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductRepository {
    // ↓いらなそうだけど一応置いてる
    List<Product> selectAll();

    // 修正ボタンが押下されて商品修正(入力)画面を開くとき：IDで商品を1件だけ特定して持ってくる
    Product selectById(Integer id);

    // 修正して「完了」ボタンを押したとき：商品データを上書き更新する
    void editProduct(Product product);

    // 修正して「完了」ボタンを押したとき：在庫データを上書き更新する
    void editStock(ProductStock productStock);

    // 確認画面へ進むとき：選択されたカテゴリIDが本当にマスタに存在するかチェックする。存在しないIDで書き換えられた場合のため。↓@paramをつけるべき？
    boolean existsCategory(@Param("categoryId") Integer categoryId);

    // ↑@Paramここでいい？

    // カテゴリをプルダウンに表示するために、すべてのカテゴリーマスタを取得する
    List<ProductCategory> selectAllCategories();
}
