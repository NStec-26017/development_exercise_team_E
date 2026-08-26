package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
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
    public void createTest_OK() {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setName("事務用品");

        Boolean actual = productCategoryRepository.create(productCategory);

        assertTrue(actual);
    }

    @Test
    public void selectAllTest_OK() {
        List<ProductCategory> actual = productCategoryRepository.selectAll();
        assertNotNull(actual);
        assertEquals(4, actual.size());

        assertEquals(10001, actual.get(0).getId());
        assertEquals("文具", actual.get(0).getName());

        assertEquals(10002, actual.get(1).getId());
        assertEquals("雑貨", actual.get(1).getName());

        assertEquals(10003, actual.get(2).getId());
        assertEquals("パソコン周辺機器", actual.get(2).getName());

        assertEquals(10004, actual.get(3).getId());
        assertEquals("事務用品", actual.get(3).getName());

    }

    @Test
    public void existsByNameTest_OK() {
        String expectedName = "事務用品";

        Boolean actual = productCategoryRepository.existByName(expectedName);

        Assertions.assertTrue(actual);
    }

    // uc010
    @Test
    public void selectAllCategoryTenTest() {
        List<ProductCategory> actual = productCategoryRepository.selectAllCategoryTen();
        assertNotNull(actual);
        assertEquals(3, actual.size());

        assertEquals(10001, actual.get(0).getId());
        assertEquals("文具", actual.get(0).getName());

        assertEquals(10002, actual.get(1).getId());
        assertEquals("雑貨", actual.get(1).getName());

        assertEquals(10003, actual.get(2).getId());
        assertEquals("パソコン周辺機器", actual.get(2).getName());

    }

    @Test
    public void selectByIdTenTest() {
        ProductCategory actual = productCategoryRepository.selectByIdTen(10001);

        assertEquals(10001, actual.getId());
        assertEquals("文具", actual.getName());
    }
    // assertNotNull(actual);
    // assertEquals(3, actual.size());

    // assertEquals(10001, actual.get(0).getId());
    // assertEquals("文具", actual.get(0).getName());

    // assertEquals(10002, actual.get(1).getId());
    // assertEquals("雑貨", actual.get(1).getName());

    // assertEquals(10003, actual.get(2).getId());
    // assertEquals("パソコン周辺機器", actual.get(2).getName());

}
