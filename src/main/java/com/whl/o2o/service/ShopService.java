package com.whl.o2o.service;

import com.whl.o2o.dto.ImageHolder;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface ShopService {
    /**
     *
     * @param shop
     * @param imageHolder
     * @return
     * @throws ShopOperationException
     */
    ShopExecution addShop(Shop shop,ImageHolder imageHolder) throws ShopOperationException;

    /**
     * 通过shopId获取shop
     * @param shopId
     * @return
     */
    Shop getByShopId(Long shopId);

    /**
     *
     * @param shop
     * @param imageHolder
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;


    /**
     * 分页查询shop
     * @param ShopCondition
     * @param pageIndex 页码
     * @param pageSize  返回条数
     * @return
     */
    public ShopExecution getShopList(Shop ShopCondition,int pageIndex,int pageSize);


}
