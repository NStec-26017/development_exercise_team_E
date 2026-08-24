package com.example.fullness.stationary.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductStock;

@Mapper
@Repository
public interface ProductStockRepository {

    ProductStock findById(@Param("productId") Integer productId);

    List<Map<String, Object>> selectProductStockJoin();
}
