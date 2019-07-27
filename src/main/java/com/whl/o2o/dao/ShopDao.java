package com.whl.o2o.dao;

import com.whl.o2o.entity.Shop;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface ShopDao {
    /**
     * 添加店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 根据id更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
