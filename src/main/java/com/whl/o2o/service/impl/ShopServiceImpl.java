package com.whl.o2o.service.impl;

import com.whl.o2o.dao.ShopDao;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.enums.ShopStateEnum;
import com.whl.o2o.exceptions.ShopOperationException;
import com.whl.o2o.service.ShopService;
import com.whl.o2o.util.ImageUtil;
import com.whl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

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
    @Transactional//添加事务支持
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
        //检查传入的Shop以及与Shop关联的其他对象是否为空 若为空抛出异常并回滚
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        if(shop.getArea() == null){
            return new ShopExecution(ShopStateEnum.NULL_AREA);
        }
        if(shop.getUserInfo() == null){
            return new ShopExecution(ShopStateEnum.NULL_USER);
        }
        if(shop.getShopCategory() == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP_CATEGORY);
        }
        try {
            //数据库中添加shop信息的初始值
            shop.setEnableStatus(ShopStateEnum.CHECK.getState());//enableStatus初始为0表示店铺状态审核中
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum<=0){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if(shopImgInputStream != null){
                    try {
                        //存储图片：对传入的shopImg进行图片处理以及之前没有附带图片地址的shop进行图片地址的添加
                        //在insertShop成功后，可以通过该shop对象获取主键值（参考mapper中的useGeneratedKeys="true"字段），对shopImgInputStream图片文件流进行相应的存储
                        addShopImgInputStream(shop,shopImgInputStream,fileName);
                        //由于java方法中的对象属性为引用传递，所以我们在addShopImgInputStream中对shop做出的改变会在方法执行完成后保留，若是基本数据类型属性则为值的拷贝传递，在方
                        //法执行完成后对该基本数据类型做出的改变不会进行保留
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImgInputStream error:"+e.getMessage());
                    }
                    //对新增地址的shop进行更新
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error:"+e.getMessage());
        }
        //shop添加成功后，返回一个待检查的状态和该shop对象
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    /**
     * 对传入的shopImg进行图片处理以及之前没有附带图片地址的shop进行图片地址的添加
     * @param shop
     * @param shopImgInputStream
     */
    private void addShopImgInputStream(Shop shop, InputStream shopImgInputStream,String fileName) {
        //通过shopId获取shop图片目录的相对值路径  upload/item/shop/"+shopId+"/"
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //对shopImg进行相应的thumbnailator图片处理，然后将其图片的绝对路径返回到shopImgAddr
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        //对shop的图片地址进行更新
        shop.setShopImg(shopImgAddr);
    }
}
