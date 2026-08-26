package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 商品(product)テーブル用Formクラス
 * 
 */
@Data
public class ProductForm implements Serializable {
    /** 商品ID */
    private Integer id;
    /** 商品カテゴリID（外部キー） */
    @NotNull(message = "カテゴリを選択してください")
    private Integer productCategoryId;
    /** 商品名 */
    @NotBlank(message = "商品名を入力してください")
    @Size(min = 2, max = 20, message = "商品名は2~20文字で入力してください")
    private String name;
    /** 価格 */
    @NotNull(message = "価格を入力してください")
    @Max(value = 1000000, message = "価格は100万以下で入力してください")
    private Integer price;
    /** 画像URL */
    private String imagePath;
    /** 削除フラグ */
    private Integer deleteFlag;

}
