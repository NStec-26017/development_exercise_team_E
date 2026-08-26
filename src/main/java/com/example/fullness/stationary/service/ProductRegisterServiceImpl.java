package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.exception.BusinessException;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

/**
 * 
 */
@Service
@Transactional(readOnly = true)
public class ProductRegisterServiceImpl implements ProductRegisterService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> selectAllCategoryTen() {
        return productCategoryRepository.selectAllCategoryTen();
    }

    @Override
    public ProductCategory selectByIdTen(Integer id) {
        return productCategoryRepository.selectByIdTen(id);
    }

    @Override
    @Transactional
    public Integer insertProduct(Product product, ProductStock productStock) {
        int productCount = productRepository.insertProductTen(product);
        if (productCount != 1) {
            throw new BusinessException("商品の登録に失敗しました");
        }

        productStock.setProductId(product.getId());
        int stockCount = productStockRepository.insertProductStockTen(productStock);
        if (stockCount != 1) {
            throw new BusinessException("商品在庫の登録に失敗しました");
        }
        return product.getId();
    }
}
