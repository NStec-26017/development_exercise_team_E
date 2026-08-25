package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductCategoryRepository;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;

@Service
public class ProductEditServiceImpl implements ProductEditService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /* productテーブル、product_stock テーブルから情報を取得し、両方のデータをFormにまとめて返す */
    @Override
    public ProductEditForm getEditForm(Integer productId) {
        // 1. productテーブルから取得
        Product product = productRepository.selectById(productId);

        // 2. product_stockテーブルから取得
        ProductStock stock = productStockRepository.selectByProductId(productId);

        // DBエラーや情報取得できなかったときのエラーを書き込む？

        // 3. Form に詰める
        ProductEditForm form = new ProductEditForm();
        form.setId(product.getId());
        form.setName(product.getName());
        form.setPrice(product.getPrice());
        form.setStock(stock.getQuantity());
        form.setCategoryId(product.getProductCategoryId());
        form.setImagePath(product.getImagePath());
        form.setDeleteFlag(product.getDeleteFlag());

        return form;
    }

    /* productテーブル、product_stock テーブルの情報をDBに更新する */
    @Override
    public void updateProduct(Integer productId, ProductEditForm form) {
        // 1. Productエンティティに詰める
        Product product = new Product();
        product.setId(productId);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setProductCategoryId(form.getCategoryId());
        product.setImagePath(form.getImagePath());
        product.setDeleteFlag(form.getDeleteFlag());

        // 2. productテーブルを更新
        productRepository.updateProduct(product);

        // 3. ProductStockエンティティに詰める
        ProductStock stock = new ProductStock();
        stock.setProductId(productId);
        stock.setQuantity(form.getStock());

        // 4. product_stockテーブルを更新
        productStockRepository.updateStock(stock);
    }

    // カテゴリ一件取得
    @Override
    public ProductCategory findCategoryById(Integer categoryId) {
        return productCategoryRepository.selectByCategoryId(categoryId);
    }
}