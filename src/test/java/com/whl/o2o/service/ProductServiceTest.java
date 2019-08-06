package com.whl.o2o.service;

import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;


    @Test
    public void testAddProduct()throws ShopOperationException{
        Shop shop = new Shop();
        shop.setShopId(1L);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(3L);


        Product product1 = new Product();
        product1.setShop(shop);
        product1.setProductCategory(productCategory);
        product1.setImgAddr("testAddr");
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试商品1desc");
        product1.setNormalPrice("1");
        product1.setPromotionPrice("1");
        product1.setPriority(1);

        File thumbnailFile = new File("")
    }
}
