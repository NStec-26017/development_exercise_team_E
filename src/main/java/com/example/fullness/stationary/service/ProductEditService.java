package com.example.fullness.stationary.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;
import com.example.fullness.stationary.repository.ProductStockRepository;
import com.example.fullness.stationary.helper.ImageEditHelper;

/*
 * UC012 「商品修正」のServiceクラス
 * 作業中
 */

@Service
public class ProductEditService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    // @Autowired
    // private ImageEditHelper imageEditHelper;

    public Product findById(Integer productId) {
        return productRepository.selectById(productId);
    }

    // // 商品データを上書き保存するためにSQLを実行する処理 理解してるようでしてない
    // Product product = new Product();
    // product.setId(form.getId());
    // product.setName(form.getName());
    // product.setPrice(form.getPrice());
    // product.setProductCategoryId(form.getProductCategoryId());
    // product.setImageUrl(finalImageUrl);
    // product.setDeleteFlag(0);

    // productRepository.editProduct(product);

    // // 在庫データを上書き保存するためにSQLを実行する処理。
    // ProductStock stock = new ProductStock();
    // stock.setProductId(form.getId());
    // stock.setQuantity(form.getQuantity());

    // productRepository.editStock(stock);

}
