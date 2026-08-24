package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductStockRepository productStockRepository;

    // 商品取得（存在しない場合は nullを返す）
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductStock findStockByProductld(Integer productld) {
        return productStockRepository.findById(productld);
    }

    // 論理削除（delete_flag = 1）
    public void logicalDelete(Integer id) {
        productRepository.updateDeleteFlag(id);
    }
}