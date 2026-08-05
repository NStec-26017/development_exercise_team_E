package com.example.fullness.stationary.form;

/**
 * BP006【商品検索画面】で選択されて送信される内容を保持するform
 */
public class ProductSearchForm {

    /** 商品カテゴリID */
    private Integer categoryId;

    /** 商品名キーワード */
    private String productName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
