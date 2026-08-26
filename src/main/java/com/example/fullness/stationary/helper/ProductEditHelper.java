package com.example.fullness.stationary.helper;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Component;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.form.ProductEditForm;

/*
 * UC012「商品修正」Helper
 */
@Component
public class ProductEditHelper {

    /*
     * Formに書いたメッセージをリストにする
     */
    public List<String> toMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(error -> {
                    if ("typeMismatch".equals(error.getCode())) {
                        String errorStr = error.toString();
                        if (errorStr.contains("price")) {
                            return "正しい価格形式で入力してください";
                        }
                        if (errorStr.contains("stock")) {
                            return "正しい在庫数形式で入力してください";
                        }
                    }
                    return error.getDefaultMessage();
                })
                .toList();
    }

    /*
     * 確認画面で表示するためにカテゴリ名をFormへいれる
     */
    public ProductEditForm setupConfirmForm(ProductEditForm form, ProductCategory category) {
        if (category != null) {
            form.setCategoryName(category.getName());
        }
        return form;
    }
}