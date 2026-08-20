package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.Product;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void selectAllTest_OK() {
        List<Product> actual = productRepository.selectAll(0);

        assertNotNull(actual);
        assertEquals(10, actual.size());

        assertEquals(11, actual.get(0).getId());
        assertEquals(10001, actual.get(0).getProductCategoryId());
        assertEquals("黒鉛筆", actual.get(0).getName());
        assertEquals(150, actual.get(0).getPrice());
        assertEquals("/images/black_pen.jpg", actual.get(0).getImagePath());

        assertEquals(12, actual.get(1).getId());
        assertEquals(10001, actual.get(1).getProductCategoryId());
        assertEquals("黒ボールペン", actual.get(1).getName());
        assertEquals(150, actual.get(1).getPrice());
        assertEquals("/images/black_pen_o.jpg", actual.get(1).getImagePath());

        assertEquals(13, actual.get(2).getId());
        assertEquals(10001, actual.get(2).getProductCategoryId());
        assertEquals("赤ボールペン", actual.get(2).getName());
        assertEquals(150, actual.get(2).getPrice());
        assertEquals("/images/red_pen_o.jpg", actual.get(2).getImagePath());

        assertEquals(14, actual.get(3).getId());
        assertEquals(10001, actual.get(3).getProductCategoryId());
        assertEquals("青ボールペン", actual.get(3).getName());
        assertEquals(150, actual.get(3).getPrice());
        assertEquals("/images/blue_pen_o.jpg", actual.get(3).getImagePath());

        assertEquals(15, actual.get(4).getId());
        assertEquals(10001, actual.get(4).getProductCategoryId());
        assertEquals("青マーカー", actual.get(4).getName());
        assertEquals(200, actual.get(4).getPrice());
        assertEquals("/images/blue_maker.jpg", actual.get(4).getImagePath());

        assertEquals(16, actual.get(5).getId());
        assertEquals(10001, actual.get(5).getProductCategoryId());
        assertEquals("赤マーカー", actual.get(5).getName());
        assertEquals(200, actual.get(5).getPrice());
        assertEquals("/images/red_maker.jpg", actual.get(5).getImagePath());

        assertEquals(17, actual.get(6).getId());
        assertEquals(10001, actual.get(6).getProductCategoryId());
        assertEquals("緑マーカー", actual.get(6).getName());
        assertEquals(200, actual.get(6).getPrice());
        assertEquals("/images/green_maker.jpg", actual.get(6).getImagePath());

        assertEquals(18, actual.get(7).getId());
        assertEquals(10001, actual.get(7).getProductCategoryId());
        assertEquals("黄マーカー", actual.get(7).getName());
        assertEquals(200, actual.get(7).getPrice());
        assertEquals("/images/yellow_maker.jpg", actual.get(7).getImagePath());

        assertEquals(19, actual.get(8).getId());
        assertEquals(10001, actual.get(8).getProductCategoryId());
        assertEquals("筆ペン", actual.get(8).getName());
        assertEquals(250, actual.get(8).getPrice());
        assertEquals("/images/black_fudepen.jpg", actual.get(8).getImagePath());

        assertEquals(20, actual.get(9).getId());
        assertEquals(10001, actual.get(9).getProductCategoryId());
        assertEquals("色鉛筆12色", actual.get(9).getName());
        assertEquals(500, actual.get(9).getPrice());
        assertEquals("/images/color_pen12.jpeg", actual.get(9).getImagePath());

    }
}