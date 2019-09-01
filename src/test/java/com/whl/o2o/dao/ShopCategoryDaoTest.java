package com.whl.o2o.dao;
import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
        ShopCategory shopCategoryCondition = new ShopCategory();
//        ShopCategory shopCategory = new ShopCategory();
//        shopCategory.setShopCategoryId(4L);
//        shopCategoryCondition.setParent(shopCategory);

        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
        assertEquals(3,shopCategoryList.size());
    }
}
