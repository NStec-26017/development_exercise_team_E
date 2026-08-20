package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        ProductCategory category = new ProductCategory();
        category.setId(10001);
        category.setName("文具");

        category.setId(10002);
        category.setName("雑貨");

        category.setId(10003);
        category.setName("パソコン周辺機器");
        List<ProductCategory> expected = List.of(category);

        when(productCategoryRepository.selectAll()).thenReturn(expected);

        List<ProductCategory> acutual = productSearchServiceImpl.getProductCategories();
        assertEquals(expected, acutual);
        verify(productCategoryRepository, times(1)).selectAll();

    }

}
