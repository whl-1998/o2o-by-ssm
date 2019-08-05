package com.whl.o2o.service;

import com.whl.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface ProductCategoryService {

    /**
     * 查询某个指定店铺下的所有商品
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(Long shopId);
}
