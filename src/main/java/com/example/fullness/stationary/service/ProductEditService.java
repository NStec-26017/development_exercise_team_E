package com.example.fullness.stationary.service;

import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.ProductEditForm;

/*
 * UC012 「商品修正」のServiceクラス
 */

@Service
public interface ProductEditService {

    /*
     * 修正入力画面に表示するデータを取得Formに詰め替える
     * productテーブル、product_stock テーブルから情報を取得し、両方のデータをFormにまとめて返す
     */

    ProductEditForm getEditForm(Integer productId);
    /*
     * 商品情報と在庫数をDBに更新する
     * productテーブル、product_stock テーブルの情報を更新する
     */

    void updateProduct(Integer productId, ProductEditForm form);

    // カテゴリの一件取得
    ProductCategory findCategoryById(Integer categoryId);

}