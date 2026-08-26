package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.ProductStock;

@MybatisTest
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
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
    @Test
    public void insertProductStockTenTest() {

        ProductStock productStock = new ProductStock();
        productStock.setQuantity(220);

        int actual = productStockRepository.insertProductStockTen(productStock);

        assertEquals(1, actual);
        assertEquals(220, productStock.getQuantity());
    }
    public void selectProductStockJoinTest_Ok() {
        List<Map<String, Object>> result = productStockRepository.selectProductStockJoin();
        // 結果が空でないことを確認
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // 1件目を確認
        Map<String, Object> actual = result.get(0);
        // 必須項目が取得できているかチェック

        assertTrue(actual.containsKey("name"));
        assertTrue(actual.containsKey("price"));
        assertTrue(actual.containsKey("image_url"));
        assertTrue(actual.containsKey("quantity"));
        assertTrue(actual.containsKey("name"));
        // delete_flag が 0 の商品だけが返ってくることを確認
        assertEquals(0, ((Number) actual.get("delete_flag")).intValue());
    }

    @Test
    void findByIdTest_Ok() {

        ProductStock actual = productStockRepository.findById(11);
        Integer id = 1;
        Integer productId = 11;
        Integer quantity = 10;

        Assertions.assertEquals(id, actual.getId());
        Assertions.assertEquals(productId, actual.getProductId());
        Assertions.assertEquals(quantity, actual.getQuantity());
    }

}
