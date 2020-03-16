package com.whl.o2o.service.impl;

import com.whl.o2o.dao.LocalAuthDao;
import com.whl.o2o.dto.LocalAuthExecution;
import com.whl.o2o.entity.LocalAuth;
import com.whl.o2o.enums.LocalAuthStateEnum;
import com.whl.o2o.exceptions.LocalAuthOperationException;
import com.whl.o2o.service.LocalAuthService;
import com.whl.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalAuthByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null || localAuth.getUserInfo() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        LocalAuth tempAuth = localAuthDao.queryLocalAuthByUserId(localAuth.getUserInfo().getUserId());
        if (tempAuth != null) {
            return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
        }
        try {
            localAuth.setCreateTime(new Date());
            localAuth.setUpdateTime(new Date());
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("账号绑定失败");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("bind localAuth fail: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword, Date updateTime) throws LocalAuthOperationException {
        if (userId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)) {
            try {
                int effectedNum = localAuthDao.updateLocalAuth(userId, username, password, MD5.getMd5(newPassword), new Date());
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("modify localAuth fail: " + e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalAuthByUserNameAndPwd(username, MD5.getMd5(password));
    }
}
