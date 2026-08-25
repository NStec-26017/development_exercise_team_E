package com.example.fullness.stationary.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

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
    @Mock
    private ProductStockRepository productStockRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    private ProductStock productStock;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(11);
        product.setProductCategoryId(10001);
        product.setName("黒鉛筆");
        product.setPrice(150);
        product.setImagePath("black_pen.jpg");
        product.setDeleteFlag(1);

        productStock = new ProductStock();
        productStock.setId(1);
        productStock.setProductId(11);
        productStock.setQuantity(10);

    }

    @Test
    void findById_Ok() {
        // リポジトリが product を返す
        when(productRepository.findById(1)).thenReturn(product);
        Product result = productService.findById(1);

        // 期待通りの値が返されているか、リポジトリが呼び出されたか
        assertNotNull(result);
        assertEquals(11, result.getId());
        assertEquals("黒鉛筆", result.getName());
        assertEquals(10001, result.getProductCategoryId());
        assertEquals(150, result.getPrice());
        assertEquals("black_pen.jpg", result.getImagePath());
        assertEquals(1, result.getDeleteFlag());
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void findProductStockById_Ok() {
        when(productStockRepository.findById(1)).thenReturn(productStock);
        ProductStock result = productService.findProductStockById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(11, result.getProductId());
        assertEquals(10, result.getQuantity());
        verify(productStockRepository, times(1)).findById(1);
    }

    @Test
    void logicalDelete_Ok() {
        productService.logicalDelete(1);
        verify(productRepository, times(1)).updateDeleteFlag(1);
    }

}
