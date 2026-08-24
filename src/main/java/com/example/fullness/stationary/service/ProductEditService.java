package com.example.fullness.stationary.service;

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
public interface ProductEditService {
    /**
     * 商品修正画面用のFormデータを取得
     * 
     * @param productId 商品ID
     * @return ProductEditForm
     */

    ProductEditForm getEditForm(Integer productId);
}