package com.whl.o2o.dao;
import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字进行顺序执行
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testBQueryProductDao(){
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(20L);
        assertEquals(1,productCategoryList.size());
        productCategoryList = productCategoryDao.queryProductCategoryList(1L);
        assertEquals(10,productCategoryList.size());
    }

    @Test
    public void testABatchInsertProductCategory(){
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品类别5");
        productCategory1.setPriority(12);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(1L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别9");
        productCategory2.setPriority(11);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);

        List<ProductCategory> categoryList = new ArrayList<>();
        categoryList.add(productCategory1);
        categoryList.add(productCategory2);

        int effectedNum = productCategoryDao.batchInsertProductCategory(categoryList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testCDeleteProductCategory(){
        Long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for(ProductCategory productCategory : productCategoryList){
            if ("商品类别5".equals(productCategory.getProductCategoryName()) || "商品类别9".equals(productCategory.getProductCategoryName())) {
                int effectedNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(),shopId);
                assertEquals(1,effectedNum);
            }
        }
    }
}
