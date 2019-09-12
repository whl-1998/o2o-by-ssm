package com.whl.o2o.dao;
import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.entity.WeChatAuth;
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
public class WeChatAuthDaoTest extends BaseTest {

    @Autowired
    private WeChatAuthDao weChatAuthDao;

    @Test
    public void testAInsertWeChatAuth(){
        WeChatAuth weChatAuth = new WeChatAuth();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(2L);
        weChatAuth.setUserInfo(userInfo);

        weChatAuth.setOpenId("ceshiopenId");
        weChatAuth.setCreateTime(new Date());
        int effectedNum = weChatAuthDao.insertWeChatAuth(weChatAuth);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryWeChatAuth(){
        WeChatAuth weChatAuth = weChatAuthDao.queryWeChatInfoByOpenId("ceshiopenId");
        System.out.println(weChatAuth.getUserInfo().getName());
    }
}
