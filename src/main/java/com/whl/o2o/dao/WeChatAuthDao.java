package com.whl.o2o.dao;

import com.whl.o2o.entity.WeChatAuth;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface WeChatAuthDao {
    /**
     * 通过openId 查询对应本平台的微信账号
     * @param openId
     * @return
     */
    WeChatAuth queryWeChatInfoByOpenId(String openId);

    /**
     * 添加对应本平台的微信账号
     * @param weChatAuth
     * @return
     */
    int insertWeChatAuth(WeChatAuth weChatAuth);

}
