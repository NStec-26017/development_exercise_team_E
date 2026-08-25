package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductStockForm implements Serializable {
    private Integer productId;

    @NotNull(message = "在庫数を入力してください")
    @Max(value = 1000, message = "在庫数は1000個以下で入力してください")
    private Integer quantity;
}
