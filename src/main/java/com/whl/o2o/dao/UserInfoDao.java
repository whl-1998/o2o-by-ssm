package com.whl.o2o.dao;

import com.whl.o2o.entity.UserInfo;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface UserInfoDao {

    /**
     * 通过Id查询用户
     * @param userId
     * @return
     */
    UserInfo queryUserInfoById(long userId);

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);

}
