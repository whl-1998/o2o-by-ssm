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
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLine);
}
