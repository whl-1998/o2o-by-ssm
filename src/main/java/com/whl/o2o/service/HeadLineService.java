package com.whl.o2o.service;

import com.whl.o2o.entity.HeadLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public interface HeadLineService {
    String HEAD_LINE_LIST= "headlinelist";

    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
