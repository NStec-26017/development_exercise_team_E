package com.example.fullness.stationary.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

/*
 * UC012 「商品修正」のServiceクラス
 * 作業中
 */

@Service
public class ProductEditService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    public ProductEditForm getEditForm(Integer productId) {
        // 1. product テーブルから取得
        Product product = productRepository.selectById(productId);

        // 2. product_stock テーブルから取得
        ProductStock stock = productStockRepository.selectByProductId(productId);

        // 3. Form に詰める
        ProductEditForm form = new ProductEditForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setStock(stock.getQuantity()); // ← ここ！
        form.setCategoryId(product.getProductCategoryId());
        form.setImagePath(product.getImageUrl());
        // form.setDeleteFlag(product.getDeleteFlag());

        return form;
    }
}