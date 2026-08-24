package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductRepository;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductCategory> findAll() {
        return productRepository.selectAllCategories();
    }
}