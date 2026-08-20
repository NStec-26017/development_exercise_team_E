package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productcategoryrepository;

    /**
     * 新しい商品カテゴリーを登録する業務処理
     */
    @Transactional // 万が一エラーが起きたら保存を取り消す
    public void registerCategory(ProductCategory productCategory) {

        // 1. 【業務チェック】すでに同じ名前のカテゴリがないか調べる
        // (※以前作成したexistsByNameのような仕組みをここで使う)
        if (productcategoryrepository.existByName(productCategory.getName())) {
            throw new RuntimeException("このカテゴリー名はすでに登録されています。");
        }

        // 2. 【データベース保存】問題なければ、XMLに書いたcreateメソッドを呼び出す
        // (ここで自動的にシーケンスからIDが取られ、INSERTされる)
        Boolean isSuccess = productcategoryrepository.create(productCategory);

        // 3. 【結果の確認】もし保存に失敗していたらエラーを発生させる
        if (!isSuccess) {
            throw new RuntimeException("データベースへの登録に失敗しました。");
        }
    }
}
