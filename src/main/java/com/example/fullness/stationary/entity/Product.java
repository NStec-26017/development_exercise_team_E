package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品(product)テーブル用entityクラス
 *
 */
@Data
public class Product implements Serializable {
    /** 商品ID */
    private Integer id;
    /** 商品カテゴリID（外部キー） */
    private Integer productCategoryId;
    /** 商品名 */
    private String name;
    /** 価格 */
    private Integer price;
    /** 画像URL */
    private String imagePath;
    /** 削除フラグ */
    private Integer deleteFlag;

    // テンプレートで参照される imagePath に対応する互換ゲッター
    public String getImagePath() {
        return this.imagePath;
    }
}