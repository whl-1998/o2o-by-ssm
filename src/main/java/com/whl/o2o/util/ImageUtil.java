package com.whl.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();//classpath：
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//定义时间格式
    private static final Random RANDOM = new Random();//随机数
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * CommonsMultipartFile to File
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File file = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(file);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 处理缩略图，并返回生成图片相对路径
     * @param thumbnailInputStream 传入文件
     * @param targetAddr 处理后的图片存储路径
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName,String targetAddr){
        String realFileName = getRandomFileName();//随机生成的文件名
        String extension = getFileExtension(fileName);//获取传入文件的扩展名
        makeDirPath(targetAddr);//创建目标路径文件夹
        String relativeAddr = targetAddr+realFileName+extension;//拼接目标路径+文件名
        logger.debug("current relativeAddr is:"+relativeAddr);//打印相对路径
        File dest = new File(PathUtil.getImageBasePath()+relativeAddr);//在目标图片存储根路径下的目标路径创建dest文件
        logger.debug("current complete addr is:"+PathUtil.getImageBasePath()+relativeAddr);
        try {
            //水印文件处理
            Thumbnails.of(thumbnailInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(1.0f)
                    .toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;//处理完图片后返回图片的相对路径地址
    }

    /**
     * 创建目标路径所涉及到的目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
         String realFileParentPath = PathUtil.getImageBasePath()+targetAddr;
         File dirPath = new File(realFileParentPath);
         if(!dirPath.exists()){
             dirPath.mkdirs();//递归创建文件夹
         }
    }

    /**
     * 获取输入文件流的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));//获取格式后缀
    }

    /**
     * 根据当前年月日小时分秒+5位随机数生成随机文件名
     * @return
     */
    public static String getRandomFileName() {
        //获取随机5位数
        int ranNum = RANDOM.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+ranNum;
    }
}
