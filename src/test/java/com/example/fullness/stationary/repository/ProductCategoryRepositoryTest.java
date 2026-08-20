package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.ProductCategory;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void selectAllTest_OK() {
        List<ProductCategory> actual = productCategoryRepository.selectAll();
        assertNotNull(actual);
        assertEquals(3, actual.size());

        assertEquals(10001, actual.get(0).getId());
        assertEquals("文具", actual.get(0).getName());

        assertEquals(10002, actual.get(1).getId());
        assertEquals("雑貨", actual.get(1).getName());

        assertEquals(10003, actual.get(2).getId());
        assertEquals("パソコン周辺機器", actual.get(2).getName());

    }

}
