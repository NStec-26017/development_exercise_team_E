package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
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
    public void selectAllCategoryTenTest() {

        ProductCategory category1 = new ProductCategory();
        category1.setId(10001);
        category1.setName("文具");

        ProductCategory category2 = new ProductCategory();
        category2.setId(10002);
        category2.setName("雑貨");

        ProductCategory category3 = new ProductCategory();

        category3.setId(10003);
        category3.setName("パソコン周辺機器");

        List<ProductCategory> expected = Arrays.asList(category1, category2, category3);

        when(productCategoryRepository.selectAll()).thenReturn(expected);

        List<ProductCategory> acutual = productRegisterServiceImpl.selectAllCategoryTen();
        assertEquals(expected, acutual);
        verify(productCategoryRepository, times(1)).selectAllCategoryTen();

    }

    @Test
    public void selectByIdTenTest() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(10001);
        productCategory.setName("文具");

        when(productCategoryRepository.selectByIdTen(10001)).thenReturn(productCategory);

        ProductCategory actual = productRegisterServiceImpl.selectByIdTen(10001);

        assertEquals(10001, actual.getId());
        assertEquals("文具", actual.getName());
    }

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
