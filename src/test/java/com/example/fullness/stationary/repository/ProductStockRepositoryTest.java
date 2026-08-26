package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.ProductStock;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductStockRepositoryTest {
    @Autowired
    private ProductStockRepository productStockRepository;

    // 在庫情報を一件取得
    @Test
    public void selectByProductIdTest_OK() {
        // 存在する商品IDを選択
        Integer id = 11;

        // SQL実行
        ProductStock actual = productStockRepository.selectByProductId(id);

        // 在庫データが正しく取れているか確認
        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals(11, actual.getProductId());
        assertEquals(10, actual.getQuantity());
    }

    // 在庫情報を更新
    @Test
    public void updateStockTest_OK() {
        // 1. 更新用のダミー在庫データを作成
        ProductStock productStock = new ProductStock();
        productStock.setId(1);
        productStock.setProductId(11);
        productStock.setQuantity(500);

        // 2. 更新処理を実行
        productStockRepository.updateStock(productStock);

        // 3. 更新されたデータを再度取得して、正しく変更されているか確認
        ProductStock actual = productStockRepository.selectByProductId(11);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals(11, actual.getProductId());
        assertEquals(500, actual.getQuantity());
    }
}
