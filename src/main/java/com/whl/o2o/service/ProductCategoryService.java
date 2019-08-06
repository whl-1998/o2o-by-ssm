package com.whl.o2o.service;

import com.whl.o2o.dto.ProductCategoryExecution;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.exceptions.ProductCategoryOperationException;

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

    /**
     * 批量添加商品
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 当删除后,需要将该分类下的商品的分类id置为null
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId) throws ProductCategoryOperationException;
}
