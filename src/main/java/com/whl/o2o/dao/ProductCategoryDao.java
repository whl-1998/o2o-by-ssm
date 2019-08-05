package com.whl.o2o.dao;

import com.whl.o2o.entity.ProductCategory;


import java.util.List;

public interface ProductCategoryDao {

    /**
     * 根据店铺的id查询其所属的所有商品分类
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(Long shopId);
}
