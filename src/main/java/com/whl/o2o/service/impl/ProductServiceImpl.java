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
import com.whl.o2o.util.PageCalculator;
import com.whl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Product> productList = productDao.queryProductList(productCondition,rowIndex,pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        if(productList != null){
            productExecution.setProductList(productList);
            productExecution.setCount(count);
        }else {
            productExecution.setState(ProductStateEnum.EMPTY_LIST.getState());
        }
        return productExecution;
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    /**
     * 1 若缩略图参数有值,处理缩略图
     *   若原先存在缩略图则删除后再新增,之后获取缩略图相对路径并赋值
     * 2 若商品详情图列表参数有值,对详情图列表进行同样操作
     * 3 将tb_product_img下面的该商品原先的商品详情图记录全部清除
     * 4 更新tb_product_img.tb_product
     */
    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) throws ProductOperationException {
        //空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //设置更新的默认属性
            product.setUpdateTime(new Date());
            //若缩略图不为空且原有缩略图不为空,删除原有后添加
            if(imageHolder != null){
                //获取原有缩略图信息 并删除
                Product oriProduct = productDao.queryProductById(product.getProductId());
                if(oriProduct.getImgAddr() != null){
                    ImageUtil.deleteFileOrPath(oriProduct.getImgAddr());
                }
                //添加新的缩略图
                addImageHolder(product,imageHolder);
            }
            //若详情图不为空且原有详情图不为空,删除原有后添加
            if(imageHolderList != null && imageHolderList.size() > 0){
                //删除原
                deleteImageHolderList(product.getProductId());
                //新增
                addImageHolderList(product,imageHolderList);
            }
            //更新商品信息
            try {
                int effectedNum = productDao.updateProduct(product);
                if(effectedNum<=0 ){
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败" + e.toString());
            }
        }else{
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void deleteImageHolderList(Long productId) {
        //获取productId获取原缩略图
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //删除原文件
        for(ProductImg productImg:productImgList){
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库原记录
        productImgDao.deleteProductImgByProductId(productId);
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
