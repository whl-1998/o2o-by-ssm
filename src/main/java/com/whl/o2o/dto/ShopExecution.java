package com.whl.o2o.dto;

import com.whl.o2o.entity.Shop;
import com.whl.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ShopExecution {
    private int state;//结果状态
    private String stateInfo; //状态标识,用于解释结果状态
    private int count;//店铺数量
    private Shop shop;//操作的店铺(增删改)
    private List<Shop> shopList;//shop列表(用于查询时存储一个或多个shop类)

    public ShopExecution() {
    }

    //店铺操作失败的时候使用的构造器,只返回结果状态和标识
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器  增删改
    public ShopExecution(ShopStateEnum stateEnum,Shop shop){
        this.stateInfo = stateEnum.getStateInfo();
        this.state = stateEnum.getState();
        this.shop = shop;
    }

    //成功的构造器  查询
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
