package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品テーブルを示すentity
 */

@Data
public class Product implements Serializable {
    private Integer id;
    private Integer ProductCategoryId;
    private String name;
    private Integer price;
    private char imageUrl;
    private Integer deleteFlag;

}
