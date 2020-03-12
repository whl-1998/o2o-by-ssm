package com.whl.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.o2o.cache.JedisUtil;
import com.whl.o2o.dao.AreaDao;
import com.whl.o2o.entity.Area;
import com.whl.o2o.exceptions.AreaOperationException;
import com.whl.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    @Transactional
    public List<Area> getAreaList() {
        String key = AREA_LIST_KEY;
        List<Area> areaList;
        ObjectMapper mapper = new ObjectMapper();
        //如果redis缓存中不存在key, 那么从数据库获取
        if (!jedisKeys.exists(key)) {
            areaList = areaDao.queryArea();
            //将数据库获取到的List转换为json格式(String), 并存入redis缓存
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(AREA_LIST_KEY);
            //定义jsonString转化后的Java类型 —— ArrayList<Area>
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                //将jsonString转化为上述javaType
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
