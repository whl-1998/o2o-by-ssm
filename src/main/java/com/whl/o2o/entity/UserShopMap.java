package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

//某个顾客对于这个店铺的总积分映射
@Data
public class UserShopMap {
	private Long userShopId;
	private Date createTime;
	private Integer point;
	private UserInfo user;
	private Shop shop;
}
