package com.whl.o2o.dao;

import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.LocalAuth;
import com.whl.o2o.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    private LocalAuthDao localAuthDao;

    @Test
    public void testInsert() {
        LocalAuth l = new LocalAuth();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        l.setUsername("w1998");
        l.setUserInfo(userInfo);
        l.setCreateTime(new Date());
        l.setPassword("1998");
        int s = localAuthDao.insertLocalAuth(l);
        System.out.println(s);
    }

    @Test
    public void queryById() {
        LocalAuth l = localAuthDao.queryLocalAuthByUserId(1L);
    }

    @Test
    public void update() {
        localAuthDao.updateLocalAuth(1L, "w1998", "1998", "w1998", new Date());
    }

    @Test
    public void queryByNameAndPwd() {
        localAuthDao.queryLocalAuthByUserNameAndPwd("w1998", "w1998");
    }
}
