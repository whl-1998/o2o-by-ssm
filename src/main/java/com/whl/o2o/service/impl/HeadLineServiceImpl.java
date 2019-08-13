package com.whl.o2o.service.impl;

import com.whl.o2o.dao.HeadLineDao;
import com.whl.o2o.entity.HeadLine;
import com.whl.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
