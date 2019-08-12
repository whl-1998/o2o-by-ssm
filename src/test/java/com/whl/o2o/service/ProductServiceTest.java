package com.whl.o2o.service;

import static org.junit.Assert.assertEquals;
import com.whl.o2o.BaseTest;
import com.whl.o2o.dto.ImageHolder;
import com.whl.o2o.dto.ProductExecution;
import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductCategory;
import com.whl.o2o.entity.ProductImg;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.enums.ProductStateEnum;
import com.whl.o2o.exceptions.ProductOperationException;
import com.whl.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    @Ignore
    public void testAddProduct() throws ProductOperationException, FileNotFoundException {
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
        //缩略图is
        File thumbnailFile = new File("C:\\Users\\WHL\\Desktop\\7335888740ee28a868d78bfc82412c30ce8edf89.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(),is);
        //商品详情图
        File productImg1 = new File("C:\\Users\\WHL\\Desktop\\timg.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:\\Users\\WHL\\Desktop\\timg (1).jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));

        ProductExecution productExecution = productService.addProduct(product1,imageHolder,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),productExecution.getState());
    }

    @Test
    public void testModifyProduct() throws ProductOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(3L);
        Product product = new Product();
        product.setProductId(23L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("正式商品1");
        product.setProductDesc("正式商品1desc");
        //缩略图
        File thumb = new File("C:\\Users\\WHL\\Desktop\\7335888740ee28a868d78bfc82412c30ce8edf89.jpg");
        InputStream is = new FileInputStream(thumb);
        ImageHolder imageHolder = new ImageHolder(thumb.getName(),is);
        //详情图
        File proImg1 = new File("C:\\Users\\WHL\\Desktop\\timg.jpg");
        File proImg2 = new File("C:\\Users\\WHL\\Desktop\\0a4e143a-7165-4d5a-8b6b-98c8ace88455.jpg");
        InputStream is1 = new FileInputStream(proImg1);
        InputStream is2 = new FileInputStream(proImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(proImg1.getName(),is1));
        productImgList.add(new ImageHolder(proImg2.getName(),is2));

        ProductExecution productExecution = productService.modifyProduct(product,imageHolder,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),productExecution.getState());
    }

}
