package com.whl.o2o.util;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class PathUtil {
    //获取项目图片存储的根路径
    public static String getImageBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectDev/image/";
        }else {
            basePath = "/home/whl/image/";
        }
        return basePath;
    }

    /**
     * 获取店铺图片存储子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/"+shopId+"/";
        return imagePath;
    }
}
