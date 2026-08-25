package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRepository;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceImplTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Nested
    @DisplayName("registerCategory(カテゴリ登録)のテスト")
    class RegisterCategoryTest {

        @Test
        @DisplayName("正常系：同じ名前のカテゴリが存在しない場合、正常に登録されること")
        void registerCategory_success() {
            // 1. 準備 (Given)
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName("事務用品");

            // 重複チェックで「存在しない(false)」を返し、保存処理も成功するようにモックを設定
            when(productCategoryRepository.existByName("事務用品")).thenReturn(false);
            when(productCategoryRepository.create(productCategory)).thenReturn(true);

            // 2. 実行 (When)
            productCategoryService.registerCategory(productCategory);

            // 3. 検証 (Then)
            // 重複チェックが走ったこと、および登録メソッドが呼ばれたことを確認
            verify(productCategoryRepository).existByName("事務用品");
            verify(productCategoryRepository).create(productCategory);

        }

        @Test
        @DisplayName("異常系：すでに同じ名前のカテゴリが存在する場合、RuntimeExceptionが発生すること")
        void registerCategory_duplicateError() {
            // 1. 準備 (Given)
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName("事務用品");

            // 重複チェックで「既に存在する(true)」を返すようにモックを設定
            when(productCategoryRepository.existByName("事務用品")).thenReturn(true);

            // 実行&検証 (When & Then)
            // assertThrowsを使い、指定した例外がスローされることを検証
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                productCategoryService.registerCategory(productCategory);
            });

            // エラーメッセージが実装の仕様どおりか確認
            assertEquals("入力されたカテゴリ名は既に登録されています。", exception.getMessage());

            // 異常系では「絶対に保存処理(create)が呼ばれていないこと」を検証
            verify(productCategoryRepository, never()).create(productCategory);
        }
    }
}