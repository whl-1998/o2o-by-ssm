package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 店铺授权
 */
@Data
public class ShopAuthMap {
	// 主键Id
	private Long shopAuthId;
	private String title;// 职称名
	private Integer titleFlag;// 职称符号（可用于权限控制）
	private Integer enableStatus;// 授权有效状态，0.无效 1.有效
	private Date createTime;
	private Date updateTime;
	private UserInfo employee;// 员工信息实体类
	private Shop shop;
}
