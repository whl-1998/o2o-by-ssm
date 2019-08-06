package com.whl.o2o.dto;

import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductCategoryExecution {
    private int state;//结果状态
    private String stateInfo; //状态标识,用于解释结果状态
    private int count;//店铺数量
    private ProductCategory productCategory;//操作的商品分类(增删改)
    private List<ProductCategory> productCategoryList;//商品分类列表(用于查询时存储一个或多个ProductCategory类)

    public ProductCategoryExecution() {
    }

    //店铺操作失败的时候使用的构造器,只返回结果状态和标识
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器  增删改
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,ProductCategory productCategory){
        this.stateInfo = stateEnum.getStateInfo();
        this.state = stateEnum.getState();
        this.productCategory = productCategory;
    }

    //成功的构造器  查询
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
