package com.whl.o2o.service;

import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Shop;

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
     * @param shopImgInputStream
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
