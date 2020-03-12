package com.whl.o2o.service.impl;

import com.whl.o2o.dao.ShopDao;
import com.whl.o2o.dto.ImageHolder;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.enums.ShopStateEnum;
import com.whl.o2o.exceptions.ShopOperationException;
import com.whl.o2o.service.ShopService;
import com.whl.o2o.util.ImageUtil;
import com.whl.o2o.util.PageCalculator;
import com.whl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
        //检查传入的Shop以及与Shop关联的其他对象是否为空 若为空抛出异常并回滚
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        if (shop.getArea() == null) {
            return new ShopExecution(ShopStateEnum.NULL_AREA);
        }
        if (shop.getUserInfo() == null) {
            return new ShopExecution(ShopStateEnum.NULL_USER);
        }
        if (shop.getShopCategory() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_CATEGORY);
        }
        try {
            //数据库中添加shop信息的初始值
            shop.setEnableStatus(ShopStateEnum.CHECK.getState());//enableStatus初始为0表示店铺状态审核中
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (imageHolder != null) {
                    try {
                        addShopImgInputStream(shop, imageHolder);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImgInputStream error: " + e.getMessage());
                    }
                    //将shop更新到数据库
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("店铺添加失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //当修改店铺传入新的图片时,需要将旧的图片删除
            if (imageHolder.getImage() != null && imageHolder.getImageName() != null && !"".equals(imageHolder.getImageName())) {
                Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                if (tempShop.getShopImg() != null) {
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImgInputStream(shop, imageHolder);
            }
            shop.setUpdateTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
            return new ShopExecution(ShopStateEnum.SUCCESS, shop);
        } catch (Exception e) {
            throw new ShopOperationException("modifyShop error: " + e.getMessage());
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if (shopList != null) {
            shopExecution.setShopList(shopList);
            shopExecution.setCount(count);
        } else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }

    /**
     * 图片存储系统中新增地址并保存店铺缩略图, 店铺实例对象保存相对地址
     * @param shop
     * @param imageHolder
     */
    private void addShopImgInputStream(Shop shop, ImageHolder imageHolder) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(imageHolder, dest);
        shop.setShopImg(shopImgAddr);
    }
}
