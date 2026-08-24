package com.example.fullness.stationary.form;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductStockForm implements Serializable {
    private Integer productId;

    @NotNull
    @Max(1000)
    private Integer quantity;
}
