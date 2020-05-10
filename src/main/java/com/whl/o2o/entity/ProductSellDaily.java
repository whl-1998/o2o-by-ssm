package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


/**
 * 顾客消费的商品映射
 */
@Data
public class ProductSellDaily {
	private Date createTime;// 哪天的销量，精确到天
	private Integer total;// 销量
	private Product product;// 商品信息实体类
	private Shop shop;// 店铺信息实体类
}
