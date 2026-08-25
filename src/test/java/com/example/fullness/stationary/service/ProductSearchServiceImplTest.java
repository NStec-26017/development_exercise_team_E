package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductSearchServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductSearchServiceImpl productSearchServiceImpl;

    @Test
    public void getProductCategories() {
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

        List<ProductCategory> acutual = productSearchServiceImpl.getProductCategories();
        assertEquals(expected, acutual);
        verify(productCategoryRepository, times(1)).selectAll();

    }

}
