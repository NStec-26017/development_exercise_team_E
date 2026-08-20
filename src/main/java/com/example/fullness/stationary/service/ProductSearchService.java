package com.example.fullness.stationary.service;

import java.util.List;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

/**
 * UC011「商品検索画面」の業務機能を提供するServiceインターフェース。
 * 
 * 
 * Serviceはインターフェース依存で設計・実装する。実装クラスはEmployeeAccountServiceImpl
 * 
 */
public interface ProductSearchService {
    List<Product> getProducts(int offset);

    List<Product> getProductsByCategoryId(Integer categoryId, int offset);

    List<ProductCategory> getProductCategories();

    int countProducts();

    int countProductsByCategoryId(Integer categoryId);

}
