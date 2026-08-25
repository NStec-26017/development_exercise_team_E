package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

@Service
public class ProductEditServiceImpl implements ProductEditService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    /* BP009商品修正（入力）画面へ遷移 */
    @Override
    public ProductEditForm getEditForm(Integer productId) {
        // 1. productテーブルから取得
        Product product = productRepository.selectById(productId);

        // DBエラーや情報取得できなかったときのエラーを書き込む？（私の想像）

        // 2. product_stockテーブルから取得
        ProductStock stock = productStockRepository.selectByProductId(productId);

        // 3. Form に詰める
        ProductEditForm form = new ProductEditForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setStock(stock.getQuantity());
        form.setCategoryId(product.getProductCategoryId());
        form.setImagePath(product.getImageUrl());
        form.setDeleteFlag(product.getDeleteFlag());

        return form;
    }

    /* BP009商品修正（入力）画面から情報更新 */
    @Override
    public void updateProduct(Integer productId, ProductEditForm form) {
        // 1. Productにエンティティに詰める
        Product product = new Product();
        product.setId(productId);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setProductCategoryId(form.getCategoryId());
        product.setImageUrl(form.getImagePath());
        product.setDeleteFlag(form.getDeleteFlag());

        // 2. product テーブルを更新
        productRepository.updateProduct(product);

        // 3. ProductStockエンティティに詰める
        ProductStock stock = new ProductStock();
        stock.setProductId(productId);
        stock.setQuantity(form.getStock());

        // 4. product_stockテーブルを更新
        productStockRepository.updateStock(stock);
    }

    /* BP010商品修正（確認）画面で商品名を */
    @Override
    public String getProductName(Integer productId) {
        // 1. productテーブルから取得
        Product product = productRepository.selectById(productId);

        // 2. 商品名を返す
        return product.getName();
    }
}