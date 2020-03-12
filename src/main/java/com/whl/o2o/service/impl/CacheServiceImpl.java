package com.whl.o2o.service.impl;

import com.whl.o2o.cache.JedisUtil;
import com.whl.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        //取出redis缓存中以xxx开头的所有key, 并将其保存至Set集合
        Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
        //遍历Set, 将redis缓存中对应的键值对删除
        for (String key : keySet) {
            jedisKeys.del(key);
        }
    }
}
