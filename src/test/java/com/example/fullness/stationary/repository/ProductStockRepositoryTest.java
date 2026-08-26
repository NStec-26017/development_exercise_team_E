package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.example.fullness.stationary.entity.ProductStock;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductStockRepositoryTest {
    @Autowired
    private ProductStockRepository productStockRepository;

    @Test
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
