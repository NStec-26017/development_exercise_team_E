package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceImplTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    void create_success() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("事務用品");

        when(productCategoryRepository.existByName("事務用品")).thenReturn(false);
        when(productCategoryRepository.create(productCategory)).thenReturn(true);

        productCategoryService.create(productCategory);

        verify(productCategoryRepository).create(productCategory);
    }

    @Test
    void duplicateCheck_True() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("事務用品");

        when(productCategoryRepository.existByName("事務用品")).thenReturn(true);

        Boolean result = productCategoryService.duplicateCheck(productCategory);

        assertTrue(result);
    }

    @Test
    void duplicateCheck_False() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("事務用品");

        when(productCategoryRepository.existByName("事務用品")).thenReturn(false);

        Boolean result = productCategoryService.duplicateCheck(productCategory);

        assertFalse(result);
    }
}
