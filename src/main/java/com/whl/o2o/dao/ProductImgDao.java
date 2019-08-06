package com.whl.o2o.dao;

import com.whl.o2o.entity.ProductImg;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface ProductImgDao {
    /**
     * 通过productId检索该product的所有img
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(Long productId);

    /**
     * 批量添加productImg
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 通过店铺id指定删除的店铺图片
     * @param productId
     * @return
     */
    //int deleteProductImgByProductId(Long productId);
}
