package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// BP019商品カテゴリ登録(入力)画面のform

@Data
public class CategoryForm implements Serializable {

    // カテゴリーID
    private Integer id;

    // カテゴリー名（画面から入力してもらうメインの項目）
    @NotBlank(message = "カテゴリー名を入力してください")
    @Size(min = 1, max = 30, message = "カテゴリー名は1~30文字で入力してください")
    private String name;

}
