package com.whl.o2o.service;

import com.whl.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface ShopCategoryService {
    String SHOP_CATEGORY_LIST = "shopcategorylist";

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
