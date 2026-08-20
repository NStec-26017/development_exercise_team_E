package com.example.fullness.stationary.entity;

import java.io.Serializable;
// import lombok.Data;

import lombok.Data;

/**
 * 商品カテゴリ(product_category)テーブル用Entityクラス。
 *
 */

// ↓↓@Dataがコンパイルエラーになる↓↓
@Data
public class ProductCategory implements Serializable {

    /** 商品カテゴリID。 */
    private Integer id;

    /** 商品カテゴリ名。 */
    private String name;

    // public Integer getId() {
    // return id;
    // }

    // public void setId(Integer id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }
}
