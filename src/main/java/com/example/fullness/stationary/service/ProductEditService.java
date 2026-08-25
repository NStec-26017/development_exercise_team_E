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
     * 修正入力画面のFormデータを取得
     * productテーブル、product_stock テーブルから情報を取得し、両方のデータをFormにまとめて返す
     */

    ProductEditForm getEditForm(Integer productId);

    // カテゴリの1件取得
    ProductCategory findById(Integer categoryId);

    /*
     * 商品情報をDBに更新
     * productテーブル、product_stock テーブルの情報を更新
     */

    void updateProduct(Integer productId, ProductEditForm form);

    /*
     * 商品名を取得
     * 商品IDで検索して商品名を取得
     */
    String getProductName(Integer productId);

}