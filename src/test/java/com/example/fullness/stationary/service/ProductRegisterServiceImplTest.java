package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

@ExtendWith(MockitoExtension.class)
public class ProductRegisterServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private ProductStockRepository productStockRepository;

    @InjectMocks
    private ProductRegisterServiceImpl productRegisterServiceImpl;

    @Test
    public void insertProductTest() {

        Product product = new Product();
        product.setName("マウス(白)");
        product.setPrice(1200);
        product.setProductCategoryId(10003);

        ProductStock productStock = new ProductStock();
        productStock.setQuantity(220);

        doAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1);
            return 1;
        }).when(productRepository).insertProductTen(product);

        when(productStockRepository.insertProductStockTen(productStock)).thenReturn(1);

        productRegisterServiceImpl.insertProduct(product, productStock);

        assertEquals("マウス(白)", product.getName());
        assertEquals(1200, product.getPrice());
        assertEquals(10003, product.getProductCategoryId());
        assertEquals(220, productStock.getQuantity());
    }

}
