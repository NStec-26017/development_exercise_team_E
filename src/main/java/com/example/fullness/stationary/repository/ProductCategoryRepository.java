package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fullness.stationary.entity.ProductCategory;

/**
 * 商品カテゴリ({@link ProductCategory})に対するデータアクセスを提供するRepository。
 */
/**
 * 全件取得
 * 
 * @return 全商品カテゴリのリスト
 */

@Mapper
@Repository
public interface ProductCategoryRepository {
    // カテゴリ全件取得
    List<ProductCategory> selectAll();

    // カテゴリ一件取得（確認画面でカテゴリ名表示するのに利用）
    ProductCategory selectByCategoryId(@Param("categoryId") Integer categoryId);
    /**
     * uc010
     * 商品カテゴリを全件取得する
     * 
     */
    List<ProductCategory> selectAllCategoryTen();

    /**
     * uc010
     * 商品カテゴリIDを指定して商品カテゴリを１件取得する
     * 
     * @param id
     * @return
     */
    ProductCategory selectByIdTen(Integer id);

}
    // @param name 商品カテゴリ名
    // @return 登録済みの場合はtrue

    Boolean existByName(String name);

    // 商品カテゴリを新規登録
    // @param name 登録するカテゴリ名
    // @return 登録に成功した場合はtrue

    Boolean create(ProductCategory productCategory);

}
