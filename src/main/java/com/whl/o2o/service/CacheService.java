package com.whl.o2o.service;


/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface CacheService {
    /**
     * 根据key前缀删除所有匹配前缀的key-value
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);
}
