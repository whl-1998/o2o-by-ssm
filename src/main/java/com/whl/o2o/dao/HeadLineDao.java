package com.whl.o2o.dao;

import com.whl.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface HeadLineDao {

    /**
     * 获取所有头条实体列表
     * @param headLine
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLine);
}
