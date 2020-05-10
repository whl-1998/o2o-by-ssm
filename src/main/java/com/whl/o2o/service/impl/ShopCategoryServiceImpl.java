package com.whl.o2o.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.o2o.cache.JedisUtil;
import com.whl.o2o.dao.ShopCategoryDao;
import com.whl.o2o.entity.ShopCategory;
import com.whl.o2o.exceptions.ShopCategoryOperationException;
import com.whl.o2o.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SHOP_CATEGORY_LIST;
        List<ShopCategory> shopCategoryList;
        ObjectMapper mapper = new ObjectMapper();
        if (shopCategoryCondition == null) {
            // 若查询条件为空, 意味着检索所有ShopCategory, 将key设置为shopcategorylist + _allfirstlevel
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null  && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            // 若parentId为非空, 则key设置为将key设置为shopcategorylist + _parent + 父类别的Id
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            // 列出所有子类别, 将key设置为shopcategorylist + _allsecondlevel
            key = key + "_allsecondlevel";
        }
        // 判断key是否存在
        if (!jedisKeys.exists(key)) {
            // 若不存在，则从数据库里面取出相应数据
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            // 将相关的实体类集合转换成string,存入redis里面对应的key中
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
