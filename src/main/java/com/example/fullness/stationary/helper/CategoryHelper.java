package com.example.fullness.stationary.helper;

import org.springframework.stereotype.Component;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.CategoryForm;

@Component
public class CategoryHelper {

    /**
     * 画面から受け取った「Form」を、DB保存用の「Entity」に変換する
     */
    public ProductCategory convertToEntity(CategoryForm form) {
        ProductCategory productcategory = new ProductCategory();

        // Formの中身を Entity に詰め替える
        // ↓↓setId、setNameがコンパイルエラーになる↓↓
        // productcategory.setId(form.getId());
        // productcategory.setName(form.getName());

        return productcategory;
    }

    /**
     * DBから取得した「Entity」を、画面表示用の「Form」に変換する（編集画面などで使用）
     */
    public CategoryForm convertToForm(ProductCategory productcategory) {
        CategoryForm form = new CategoryForm();

        // ↓↓getId、getNameがコンパイルエラーになる↓↓
        // form.setId(productcategory.getId());
        // form.setName(productcategory.getName());

        return form;
    }
}
