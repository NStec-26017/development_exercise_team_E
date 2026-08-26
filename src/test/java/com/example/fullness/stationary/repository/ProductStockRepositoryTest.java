package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.ProductStock;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class ProductStockRepositoryTest {
    @Autowired
    private ProductStockRepository productStockRepository;

    @Test
    public void insertProductStockTenTest() {

        ProductStock productStock = new ProductStock();
        productStock.setQuantity(220);

        int actual = productStockRepository.insertProductStockTen(productStock);

        assertEquals(1, actual);
        assertEquals(220, productStock.getQuantity());
    }
}
