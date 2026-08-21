package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.fullness.stationary.repository.ProductRepository;

@Service
public class ProductDeleteService {
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public void deleteById(int id) {
        // 1. リポジトリの deleteById を実行し、更新件数を取得
        int updatedCount = productRepository.deleteById(id);
        // 2. 更新件数が 0 件の場合は、対象データがない（または既削除）ため例外をスロー
        if (updatedCount == 0) {
            throw new RuntimeException("指定された商品が見つからないか、既に削除されています。ID: " + id);
        }
    }
}