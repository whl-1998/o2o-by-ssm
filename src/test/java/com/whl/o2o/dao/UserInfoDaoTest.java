package com.whl.o2o.dao;
import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.UserInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserInfoDaoTest extends BaseTest {
    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void testAInsertUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("whlAJ");
        userInfo.setCreateTime(new Date());
        userInfo.setEmail("313576743@qq.com");
        userInfo.setEnableStatus(1);
        userInfo.setGender("boy");
        userInfo.setUpdateTime(new Date());
        userInfo.setUserType(1);
        int effectedNum = userInfoDao.insertUserInfo(userInfo);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryUserInfoById(){
        long userId = 2L;
        UserInfo userInfo = userInfoDao.queryUserInfoById(userId);
        System.out.println(userInfo.getUsername());
    }
}
