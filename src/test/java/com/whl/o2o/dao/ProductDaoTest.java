package com.whl.o2o.dao;

import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void testAInsertProduct(){
        Shop shop1 = new Shop();
        shop1.setShopId(1L);

        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryId(3L);

        Product product1 = new Product();
        product1.setShop(shop1);
        product1.setProductCategory(productCategory1);
        product1.setImgAddr("testAddr");
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试商品1desc");
        product1.setNormalPrice("1");
        product1.setPromotionPrice("1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());

        Product product2 = new Product();
        product2.setShop(shop1);
        product2.setProductCategory(productCategory1);
        product2.setImgAddr("testAddr");
        product2.setProductName("测试商品2");
        product2.setPriority(1);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setUpdateTime(new Date());

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1,effectedNum);


    }
}
