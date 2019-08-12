package com.whl.o2o.dao;

import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.entity.ProductImg;
import com.whl.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct(){
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryId(3L);

        Product product1 = new Product();
        product1.setShop(shop1);
        product1.setProductCategory(productCategory1);
        product1.setImgAddr("testImgAddr1");
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试商品1desc");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setUpdateTime(new Date());

        Product product2 = new Product();
        product2.setProductCategory(productCategory1);
        product2.setProductName("测试商品2");
        product2.setProductDesc("测试商品2desc");
        product2.setImgAddr("testImgAddr2");
        product2.setPriority(2);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setUpdateTime(new Date());
        product2.setShop(shop1);

        Product product3 = new Product();
        product3.setProductCategory(productCategory1);
        product3.setProductName("测试商品3");
        product3.setProductDesc("测试商品3desc");
        product3.setImgAddr("testImgAddr3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setUpdateTime(new Date());
        product3.setShop(shop1);

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1,effectedNum);

        effectedNum = productDao.insertProduct(product2);
        assertEquals(1,effectedNum);

        effectedNum = productDao.insertProduct(product3);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryProductList(){
        Product productCondition = new Product();
        List<Product> productList = productDao.queryProductList(productCondition,0,5);
        assertEquals(4,productList.size());
        int count = productDao.queryProductCount(productCondition);
        assertEquals(4,count);
        productCondition.setProductName("押尾");
        productList = productDao.queryProductList(productCondition,0,5);
        assertEquals(1,productList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(1,count);
    }

    @Test
    @Ignore
    public void testCQueryProductByProductId(){
        Long productId = 1L;
        //批量添加productImg
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2");
        productImg2.setImgDesc("测试图片2");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);
        List<ProductImg> productImgList = new ArrayList<>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);
        //根据productId检索product,并查看该product是否包含2个商品详情图
        Product product = productDao.queryProductById(productId);
        assertEquals(2,product.getProductImgList().size());
        //根据productId删除productImg
        effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testDUpdateProduct() throws RuntimeException{
        Product product = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(20L);
        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setProductId(20L);
        product.setShop(shop);
        product.setProductName("宫保鸡丁");
        product.setProductCategory(productCategory);
        product.setUpdateTime(new Date());
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1,effectedNum);
    }
}
