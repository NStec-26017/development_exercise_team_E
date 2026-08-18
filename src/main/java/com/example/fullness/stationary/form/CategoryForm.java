package com.example.fullness.stationary.form;

public class CategoryForm {

    // ① カテゴリーID（新規登録の時は空っぽ、編集・削除の時に使います）
    private Integer id;

    // ② カテゴリー名（画面から入力してもらうメインの項目）

    // ↓↓@NotBlank、@Sizeがコンパイルエラーになる↓↓
    // @NotBlank(message = "カテゴリー名を入力してください")
    // @Size(max = 20, message = "カテゴリー名は1~30文字で入力してください")
    private String name;

    // --- 以下、ゲッターとセッター（Lombokの@Dataを使う場合は不要です） ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
