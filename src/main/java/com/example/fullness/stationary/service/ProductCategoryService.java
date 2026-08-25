package com.example.fullness.stationary.service;

import com.example.fullness.stationary.entity.ProductCategory;

public interface ProductCategoryService {

    void registerCategory(ProductCategory productCategory);

    Boolean duplicateCheck(ProductCategory productCategory);
}
