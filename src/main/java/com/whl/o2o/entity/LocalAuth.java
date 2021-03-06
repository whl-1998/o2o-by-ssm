package com.whl.o2o.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Data
public class LocalAuth {
    private Long localAuthId;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private UserInfo userInfo;
}
