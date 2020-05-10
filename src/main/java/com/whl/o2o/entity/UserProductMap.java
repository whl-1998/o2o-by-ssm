package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

//顾客消费的商品映射
@Data
public class UserProductMap {
	private Long userProductId;
	private Date createTime;
	private Integer point;
	private UserInfo user;
	private Product product;
	private Shop shop;
	private UserInfo operator;
}
