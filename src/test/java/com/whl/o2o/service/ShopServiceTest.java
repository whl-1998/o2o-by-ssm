package com.whl.o2o.service;

import com.whl.o2o.BaseTest;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Area;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.entity.ShopCategory;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.enums.ShopStateEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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
    public void testGetShopList(){
        Shop shopCondition = new Shop();
        //从第0行开始查询返回5条数据
        Area area = new Area();
        area.setAreaId(3);
        shopCondition.setArea(area);
        ShopExecution shopExecution = shopService.getShopList(shopCondition,1,2);

        System.out.println("new shopList Size："+shopExecution.getShopList().size());
        System.out.println("new shop count："+shopExecution.getCount());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(2L);
        Area area = new Area();
        area.setAreaId(3);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);


        shop.setUserInfo(userInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);

        shop.setShopName("朝鲜族风味");
        shop.setShopDesc("朝鲜族风味desc");
        shop.setShopAddr("大食堂2楼");
        shop.setPhone("15542381883");
        shop.setAdvice("审核中");
        shop.setPriority(1);
        File shopImg = new File("C:\\Users\\WHL\\Desktop\\0a4e143a-7165-4d5a-8b6b-98c8ace88455.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution =  shopService.addShop(shop,is,shopImg.getName());//执行成功后返回一个包含CHECK状态和shop实例的shopExecution
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("modify");
        File shopImg = new File("C:\\Users\\WHL\\Desktop\\7335888740ee28a868d78bfc82412c30ce8edf89.jpg");
        InputStream is = new FileInputStream(shopImg);

        ShopExecution shopExecution = shopService.modifyShop(shop,is,"zhj.jpg");
        System.out.println("new img des:"+shopExecution.getShop().getShopImg());
    }
}
