package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.exception.ProductSearchException;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Product> getProducts(int offset) {

        try {
            return productRepository.selectAll(offset);
        } catch (BadSqlGrammarException e) {
            throw new ProductSearchException("商品データの取得に失敗しました。", e);
        } catch (PermissionDeniedDataAccessException e) {
            throw new ProductSearchException("この操作を行う権限がありません。", e);
        } catch (DataAccessException e) {
            throw new ProductSearchException("システムエラーが発生しました。管理者に連絡してください。", e);
        }

    }

    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId, int offset) {
        return productRepository.findByCategoryId(categoryId, offset);

    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.selectAll();
    }

    @Override
    public int countProducts() {
        return productRepository.countAll();
    }

    @Override
    public int countProductsByCategoryId(Integer categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }

}