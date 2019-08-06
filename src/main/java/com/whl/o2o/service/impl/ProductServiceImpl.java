package com.whl.o2o.service.impl;

import com.whl.o2o.dao.ProductDao;
import com.whl.o2o.dao.ProductImgDao;
import com.whl.o2o.dto.ImageHolder;
import com.whl.o2o.dto.ProductExecution;
import com.whl.o2o.entity.Product;
import com.whl.o2o.entity.ProductImg;
import com.whl.o2o.enums.ProductStateEnum;
import com.whl.o2o.exceptions.ProductOperationException;
import com.whl.o2o.service.ProductService;
import com.whl.o2o.util.ImageUtil;
import com.whl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            product.setEnableStatus(1);
            //若商品缩略图不为空则添加
            if(imageHolder != null){
                addImageHolder(product,imageHolder);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败:"+e.toString());
            }
            //若商品详情图不为空则添加
            if (imageHolderList != null && imageHolderList.size() != 0){
                addImageHolderList(product,imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void addImageHolder(Product product, ImageHolder imageHolder) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(imageHolder,dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void addImageHolderList(Product product, List<ImageHolder> imageHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for(ImageHolder productImageHolder: imageHolderList){
            String imgAddr = ImageUtil.generateThumbnail(productImageHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if(productImgList.size()>0){
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品详情图失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品详情图失败:"+e.toString());
            }
        }
    }
}
