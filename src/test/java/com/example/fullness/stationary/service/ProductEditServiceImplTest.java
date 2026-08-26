package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

@ExtendWith(MockitoExtension.class)
public class ProductEditServiceImplTest {

    @InjectMocks
    private ProductEditServiceImpl productEditServiceImpl;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductStockRepository productStockRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void getEditFormTest_OK() {

        Product product = new Product();
        product.setId(11);
        product.setName("黒鉛筆");
        product.setPrice(150);
        product.setProductCategoryId(10001);
        product.setImagePath("black_pen.jpg");
        product.setDeleteFlag(0);

        ProductStock stock = new ProductStock();
        stock.setProductId(11);
        stock.setQuantity(10);

        when(productRepository.selectById(11)).thenReturn(product);
        when(productStockRepository.selectByProductId(11)).thenReturn(stock);

        ProductEditForm actualForm = productEditServiceImpl.getEditForm(11);

        assertEquals(11, actualForm.getId());
        assertEquals("黒鉛筆", actualForm.getName());
        assertEquals(150, actualForm.getPrice());
        assertEquals(10, actualForm.getStock());
        assertEquals(10001, actualForm.getCategoryId());
        assertEquals("black_pen.jpg", actualForm.getImagePath());
        assertEquals(0, actualForm.getDeleteFlag());

        verify(productRepository, times(1)).selectById(11);
        verify(productStockRepository, times(1)).selectByProductId(11);
    }

    // 画面から入力されたデータ（Form）をEntityに変換して更新メソッドに渡せているか
    // 商品情報、在庫情報をまとめて正しく更新できているか
    @Test
    public void updateProductTest_OK() {

        ProductEditForm form = new ProductEditForm();
        form.setName("黒鉛筆(1ダース)");
        form.setPrice(1000);
        form.setStock(100);
        form.setCategoryId(10002);
        form.setImagePath("black_pen_1dozen.jpg");
        form.setDeleteFlag(0);

        productEditServiceImpl.updateProduct(11, form);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository, times(1)).updateProduct(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();

        assertEquals(11, capturedProduct.getId());
        assertEquals("黒鉛筆(1ダース)", capturedProduct.getName());
        assertEquals(1000, capturedProduct.getPrice());
        assertEquals(10002, capturedProduct.getProductCategoryId());
        assertEquals("black_pen_1dozen.jpg", capturedProduct.getImagePath());
        assertEquals(0, capturedProduct.getDeleteFlag());

        ArgumentCaptor<ProductStock> stockCaptor = ArgumentCaptor.forClass(ProductStock.class);
        verify(productStockRepository, times(1)).updateStock(stockCaptor.capture());
        ProductStock capturedStock = stockCaptor.getValue();

        assertEquals(11, capturedStock.getProductId());
        assertEquals(100, capturedStock.getQuantity());
    }

    // カテゴリ一件取得
    @Test
    public void findCategoryByIdTest_OK() {

        ProductCategory expectedCategory = new ProductCategory();
        expectedCategory.setId(10001);
        expectedCategory.setName("文具");

        when(productCategoryRepository.selectByCategoryId(10001)).thenReturn(expectedCategory);

        ProductCategory actualCategory = productEditServiceImpl.findCategoryById(10001);

        assertEquals(10001, actualCategory.getId());
        assertEquals("文具", actualCategory.getName());
        verify(productCategoryRepository, times(1)).selectByCategoryId(10001);
    }
}
