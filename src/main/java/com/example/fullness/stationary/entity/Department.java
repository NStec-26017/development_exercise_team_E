package com.example.fullness.stationary.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 部署テーブルを示すentity
 */
@Data
public class Department implements Serializable {
    private Integer id;
    private String name;
}
