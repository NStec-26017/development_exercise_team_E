package com.example.fullness.stationary.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductStock;

@Repository
public interface ProductStockRepository {

    List<ProductStock> selectAll();
}
