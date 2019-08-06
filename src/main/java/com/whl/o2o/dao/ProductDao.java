package com.whl.o2o.dao;

import com.whl.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 分页查询product
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    //List<Product> queryProductList(@Param("ProductCondition") Product productCondition,@Param("rowIndex")Integer rowIndex,@Param("pageSize") Integer pageSize);

    /**
     * 添加商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 更新商品
     * @param product
     * @return
     */
    int updateProduct(Product product);

}
