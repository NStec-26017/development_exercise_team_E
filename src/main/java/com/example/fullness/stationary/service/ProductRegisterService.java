package com.example.fullness.stationary.service;

import java.util.List;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

public interface ProductRegisterService {

    /**
     * uc010
     * 商品カテゴリを全件取得する
     * 
     */
    List<ProductCategory> selectAllCategoryTen();

    /**
     * uc010
     * 商品カテゴリIDを指定して商品カテゴリを１件取得する
     * 
     * @param id
     * @return
     */
    ProductCategory selectByIdTen(Integer id);

    /**
     * uc010 新商品とその在庫の登録
     * 
     * @param product
     * @param productStock
     * @return
     */
    Integer insertProduct(Product product, ProductStock productStock);
}
