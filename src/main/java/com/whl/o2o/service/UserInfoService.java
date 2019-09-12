package com.whl.o2o.service;

import com.whl.o2o.entity.UserInfo;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface UserInfoService {

    /**
     * 根据用户id获取userInfo
     * @param userId
     * @return
     */
    UserInfo getUserInfoById(Long userId);
}
