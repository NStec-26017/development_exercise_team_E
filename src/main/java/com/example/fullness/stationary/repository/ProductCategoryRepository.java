package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
@Repository
public interface ProductCategoryRepository {
    List<ProductCategory> selectAll();
}
