package com.whl.o2o.dto;

import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.enums.ProductCategoryStateEnum;
import com.whl.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductExecution {
    private int state;//结果状态
    private String stateInfo; //状态标识,用于解释结果状态
    private int count;//店铺数量
    private Product product;//操作的商品(增删改)
    private List<Product> productList;//product列表(用于查询时存储一个或多个product列表类)

    public ProductExecution() {
    }

    //店铺操作失败的时候使用的构造器,只返回结果状态和标识
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器  增删改
    public ProductExecution(ProductStateEnum stateEnum, Product product){
        this.stateInfo = stateEnum.getStateInfo();
        this.state = stateEnum.getState();
        this.product = product;
    }

    //成功的构造器  查询
    public ProductExecution(ProductStateEnum stateEnum,List<Product> productList){
        this.stateInfo = stateEnum.getStateInfo();
        this.state = stateEnum.getState();
        this.productList = productList;
    }
}
