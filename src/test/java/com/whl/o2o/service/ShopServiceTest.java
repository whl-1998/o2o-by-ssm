package com.whl.o2o.service;

import com.whl.o2o.BaseTest;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Area;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.entity.ShopCategory;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        UserInfo userInfo = new UserInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        userInfo.setUserId(1L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(2L);
        shop.setUserInfo(userInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("小红帽快餐test");
        shop.setShopDesc("小红帽快餐desc");
        shop.setShopAddr("大食堂2楼");
        shop.setPhone("15542381883");
        shop.setAdvice("审核中");
        shop.setPriority(1);
        File shopImg = new File("C:\\Users\\WHL\\Desktop\\0a4e143a-7165-4d5a-8b6b-98c8ace88455.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution =  shopService.addShop(shop,is,shopImg.getName());//执行成功后返回一个包含CHECK状态和shop实例的shopExecution
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }
}
