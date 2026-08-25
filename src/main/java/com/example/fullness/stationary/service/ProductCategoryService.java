package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // カテゴリを全件取得
    public List<ProductCategory> findAll() {
        return productCategoryRepository.selectAllCategories();
    }

    // カテゴリの情報を一件取得
    public ProductCategory findById(Integer categoryId) {
        return productCategoryRepository.selectCategoryById(categoryId);
    }
}