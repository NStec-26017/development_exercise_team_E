package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // 商品取得
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    public ProductStock findProductStockById(Integer productId) {
        return productStockRepository.findById(productId);
    }

    // 論理削除（delete_flag = 1）
    public void logicalDelete(Integer id) {
        productRepository.updateDeleteFlag(id);
    }

}