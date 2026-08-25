package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.ProductStock;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductStockRepositoryTest {
    @Autowired
    private ProductStockRepository productStockRepository; // テスト対象のMapper

    @Test
    public void selectProductStockJoinTest_Ok() {
        // 実行
        List<Map<String, Object>> result = productStockRepository.selectProductStockJoin();
        // 結果が空でないことを確認
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // 1件目を確認
        Map<String, Object> row = result.get(0);
        // 必須項目が取得できているかチェック
        assertTrue(row.containsKey("id"));
        assertTrue(row.containsKey("name"));
        assertTrue(row.containsKey("price"));
        assertTrue(row.containsKey("image_url"));
        assertTrue(row.containsKey("quantity")); // 在庫数
        assertTrue(row.containsKey("category_id")); // カテゴリID
        // delete_flag が 0 の商品だけが返ってくることを確認
        assertEquals(0, ((Number) row.get("delete_flag")).intValue());
    }
}
