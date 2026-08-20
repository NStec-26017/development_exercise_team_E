package com.example.fullness.stationary.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.form.ProductEditForm;
import com.example.fullness.stationary.repository.ProductRepository;

import com.example.fullness.stationary.helper.ImageEditHelper;

/*
 * UC012 「商品修正」のServiceクラス
 */

@Service
public class ProductEditService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageEditHelper imageEditHelper;

    public void editProductAndStock(ProductEditForm form) throws IOException {

        // ユーザーが新しい画像アップロードしたときにその画像をサーバーに保存する処理
        String finalImageUrl = form.getImageUrl();
        if (form.getImageFile() != null && !form.getImageFile().isEmpty()) {
            finalImageUrl = imageEditHelper.saveImage(form.getImageFile());
        }

        // 商品データを上書き保存するためにSQLを実行する処理 理解してるようでしてない
        Product product = new Product();
        product.setId(form.getId());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setProductCategoryId(form.getProductCategoryId());
        product.setImageUrl(finalImageUrl);
        product.setDeleteFlag(0);

        productRepository.editProduct(product);

        // 在庫データを上書き保存するためにSQLを実行する処理。
        ProductStock stock = new ProductStock();
        stock.setProductId(form.getId());
        stock.setQuantity(form.getQuantity());

        productRepository.editStock(stock);

    }
}
