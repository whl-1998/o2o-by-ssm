package com.whl.o2o.service.impl;

import com.whl.o2o.dao.UserInfoDao;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo getUserInfoById(Long userId) {
        return userInfoDao.queryUserInfoById(userId);
    }
}
