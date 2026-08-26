package com.example.fullness.stationary.form;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品カテゴリ(product_category)テーブル用Formクラス
 * 
 */
@Data
public class ProductCategoryForm implements Serializable {

    /** 商品カテゴリID */
    private Integer id;

    /** 商品カテゴリ名 */
    private String name;

}
