package com.example.fullness.stationary.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * BP009　商品修正（入力）画面のForm
 */

@Data
public class ProductEditForm implements Serializable {
    // 画面の裏側で保持する商品ID（どの商品を修正するか判断するため）
    private Integer id;

    // 1. 商品名
    @NotBlank(message = "商品名を入力してください")
    @Size(min = 2, max = 20, message = "商品名は2～20文字で入力してください")
    private String name;

    // 2. 単価
    @NotNull(message = "価格を入力してください")
    @Min(value = 0, message = "正しい価格形式で入力してください")
    @Max(value = 1000000, message = "価格は100万円以下で入力してください")
    private Integer price;

    // 3. 在庫数
    @NotNull(message = "在庫数を入力してください")
    @Min(value = 0, message = "正しい在庫数形式で入力してください")
    @Max(value = 1000, message = "在庫数は1000個以下で入力してください")
    private Integer quantity; // Entityの「quantity」に合わせました

    // 4. カテゴリID
    @NotBlank(message = "カテゴリを選択してください")
    private Integer productCategoryId; // Entityの「productCategoryId」に合わせました

    // 5. 画像ファイル（画面からアップロードされたファイルそのものを受け取る）
    private MultipartFile imageFile;

    // 6. 現在登録されている画像のパス・URL（確認画面での表示や、画像が変更されない場合に使用）
    private String imageUrl;
}
