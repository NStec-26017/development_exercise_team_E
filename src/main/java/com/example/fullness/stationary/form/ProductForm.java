package com.example.fullness.stationary.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductForm implements Serializable {
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

}
