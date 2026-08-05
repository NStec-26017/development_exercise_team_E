package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品カテゴリーテーブルを示すentity
 */
@Data
public class ProductCategory implements Serializable {

    private Integer id;
    private String name;

}
