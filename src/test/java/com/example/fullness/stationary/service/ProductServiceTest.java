package com.example.fullness.stationary.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1);
        product.setName("黒鉛筆");
    }

    @Test
    @DisplayName("存在するIDを指定したとき、該当する商品が返されること")
    void findById_Ok() {
        // リポジトリが product を返す
        when(productRepository.findById(1)).thenReturn(product);
        Product result = productService.findById(1);

        // 期待通りの値が返されているか、リポジトリが呼び出されたか
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("黒鉛筆", result.getName());
        verify(productRepository, times(1)).findById(1);
    }

}
