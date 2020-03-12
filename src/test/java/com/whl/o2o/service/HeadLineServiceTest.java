package com.whl.o2o.service;

import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class HeadLineServiceTest extends BaseTest {
    @Autowired
    private HeadLineService headLineService;

    @Test
    public void testGetHeadLineList() {
        headLineService.getHeadLineList(new HeadLine());
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        headLineService.getHeadLineList(headLine);
    }
}
