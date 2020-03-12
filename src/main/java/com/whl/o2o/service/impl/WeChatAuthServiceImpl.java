package com.whl.o2o.service.impl;

import com.whl.o2o.dao.UserInfoDao;
import com.whl.o2o.dao.WeChatAuthDao;
import com.whl.o2o.dto.WeChatAuthExecution;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.entity.WeChatAuth;
import com.whl.o2o.enums.WeChatAuthStateEnum;
import com.whl.o2o.exceptions.WeChatAuthOperationException;
import com.whl.o2o.service.WeChatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class WeChatAuthServiceImpl implements WeChatAuthService {
    Logger logger = LoggerFactory.getLogger(WeChatAuthServiceImpl.class);

    @Autowired
    private WeChatAuthDao weChatAuthDao;
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public WeChatAuth getWeChatAuthByOpenId(String openId) {
        return weChatAuthDao.queryWeChatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WeChatAuthExecution register(WeChatAuth weChatAuth) throws WeChatAuthOperationException {
        //空值判断
        if(weChatAuth == null || weChatAuth.getOpenId() == null){
            return new WeChatAuthExecution(WeChatAuthStateEnum.NULL_AUTH_INFO);
        }
        try {
            //如果微信账号里夹带用户信息且用户id为null, 则认为该用户第一次通过微信登陆使用平台
            //自动创建账户
            if (weChatAuth.getUserInfo() != null && weChatAuth.getUserInfo().getUserId() == null) {
                try {
                    weChatAuth.setCreateTime(new Date());
                    weChatAuth.getUserInfo().setCreateTime(new Date());
                    weChatAuth.getUserInfo().setUpdateTime(new Date());
                    weChatAuth.getUserInfo().setEnableStatus(1);
                    UserInfo userInfo = weChatAuth.getUserInfo();
                    int effectedNum = userInfoDao.insertUserInfo(userInfo);
                    weChatAuth.setUserInfo(userInfo);
                    if (effectedNum <= 0) {
                        throw new WeChatAuthOperationException("添加用户信息失败");
                    }
                } catch (Exception e) {
                    logger.error("insert userInfo error: " + e.toString());
                    throw new WeChatAuthOperationException("insert userInfo error: " + e.getMessage());
                }
            }
            int effectedNum = weChatAuthDao.insertWeChatAuth(weChatAuth);
            if (effectedNum <= 0) {
                throw new WeChatAuthOperationException("账号创建失败");
            } else {
                return new WeChatAuthExecution(WeChatAuthStateEnum.SUCCESS, weChatAuth);
            }
        } catch (Exception e) {
            logger.error("insert wechatAuth error: " + e.toString());
            throw new WeChatAuthOperationException("insert wechatAuth error " + e.getMessage());
        }
    }
}
