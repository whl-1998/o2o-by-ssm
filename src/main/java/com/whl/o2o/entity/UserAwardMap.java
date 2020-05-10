package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

//顾客已领取的奖品映射
@Data
public class UserAwardMap {
	private Long userAwardId;// 主键Id
	private Date createTime;
	private Integer usedStatus;// 使用状态0.未兑换 1.已兑换
	private Integer point;// 领取奖品所消耗的积分
	private UserInfo user;// 顾客信息实体类
	private Award award;// 奖品信息实体类
	private Shop shop;// 该奖品关联的店铺
	private UserInfo operator;// 领取奖品时的关联操作员
}
