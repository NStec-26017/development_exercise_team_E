package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Product> getProducts(int offset) {

        return productRepository.selectAll(offset);

    }

    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId, int offset) {
        return productRepository.findByCategoryId(categoryId, offset);

    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.selectAll();
    }

    @Override
    public int countProducts() {
        return productRepository.countAll();
    }

    @Override
    public int countProductsByCategoryId(Integer categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }

}
