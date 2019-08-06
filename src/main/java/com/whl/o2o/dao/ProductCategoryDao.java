package com.whl.o2o.dao;

import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


import java.util.List;

public interface ProductCategoryDao {

    /**
     * 根据店铺的id查询其所属的所有商品分类
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(Long shopId);


    /**
     * 批量添加productCategory
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 通过shopId和productCategoryId删除指定的商品分类
     * @param productCategoryId
     * @param shopId 增加shopId的判断是为了更安全
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
