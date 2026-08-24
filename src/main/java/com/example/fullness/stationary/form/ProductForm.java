package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductForm implements Serializable {
    /** 商品ID */
    private Integer id;
    /** 商品カテゴリID（外部キー） */
    @NotNull
    private Integer productCategoryId;
    /** 商品名 */
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    /** 価格 */
    @NotNull
    @Max(1000000)
    private Integer price;
    /** 画像URL */
    private String imagePath;
    /** 削除フラグ */
    private Integer deleteFlag;

}
