package com.example.fullness.stationary.entity;

import java.io.Serializable;
// import lombok.Data;

/**
 * 商品カテゴリ(product_category)テーブル用Entityクラス。
 *
 */

// ↓↓@Dataがコンパイルエラーになる↓↓
// @Data
public class ProductCategory implements Serializable {

    /** 商品カテゴリID。 */
    private Integer id;

    /** 商品カテゴリ名。 */
    private String name;

}
