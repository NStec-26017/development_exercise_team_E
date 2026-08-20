package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.Product;

@Mapper
@Repository
public interface ProductRepository {
    List<Product> selectAll(
            @Param("offset") int offset);

    List<Product> findByCategoryId(
            @Param("categoryId") Integer categoryId,
            @Param("offset") int offset);

    int countAll();

    int countByCategoryId(
            @Param("categoryId") Integer categoryId);
}
