package com.whl.o2o.dao;

import com.whl.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

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

    /**
     * 通过id查询shop
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);

    /**
     * 分页查询店铺,输入:店铺名,店铺类别,区域id,owner,店铺状态
     * @param shopCondition
     * @param rowIndex  从第几行开始取值
     * @param pageSize  返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回shopList总数
     * @param shopCondition
     * @return
     */
    Integer queryShopCount(@Param("shopCondition") Shop shopCondition);

}
