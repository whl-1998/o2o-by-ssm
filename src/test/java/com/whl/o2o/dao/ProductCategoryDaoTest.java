package com.whl.o2o.dao;
import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Test
    public void testQueryProductDao(){
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(20L);
        assertEquals(1,productCategoryList.size());
        productCategoryList = productCategoryDao.queryProductCategoryList(1L);
        assertEquals(2,productCategoryList.size());
    }
}
