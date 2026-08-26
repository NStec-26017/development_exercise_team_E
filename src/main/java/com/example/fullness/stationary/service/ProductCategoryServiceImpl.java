package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> selectAll() {
        return productCategoryRepository.selectAll();
    }

    @Override
    public Boolean duplicateCheck(ProductCategory productCategory) {

        // 1. 【業務チェック】すでに同じ名前のカテゴリがないか調べる
        // (※以前作成したexistsByNameのような仕組みをここで使う)
        if (productCategoryRepository.existByName(productCategory.getName())) {

            return true;
        }

        return false;
    }

    /**
     * 新しい商品カテゴリーを登録する業務処理
     */
    @Transactional // 万が一エラーが起きたら保存を取り消す
    public void create(ProductCategory productCategory) {

        // すでに同じ名前のカテゴリがないか調べる
        if (productCategoryRepository.existByName(productCategory.getName())) {
            throw new RuntimeException("入力されたカテゴリ名は既に登録されています。");
        }

        // 問題なければ、XMLに書いたcreateメソッドを呼び出す
        Boolean isSuccess = productCategoryRepository.create(productCategory);

        // もし保存に失敗していたらエラーを発生させる
        if (!isSuccess) {
            throw new RuntimeException("データベースへの登録に失敗しました。");
        }
    }
}
