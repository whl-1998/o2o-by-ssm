package com.whl.o2o.web.admin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.o2o.dto.ShopExecution;
import com.whl.o2o.entity.Shop;
import com.whl.o2o.entity.UserInfo;
import com.whl.o2o.enums.ShopStateEnum;
import com.whl.o2o.service.ShopService;
import com.whl.o2o.util.HttpServletRequestUtil;
import com.whl.o2o.util.ImageUtil;
import com.whl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;


//    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
//    @ResponseBody
//    private Map<String,Object> registerShop(HttpServletRequest request){
//        //1.接受并转化相应的参数
//        Map<String,Object> modelMap = new HashMap<String, Object>();
//        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
//        ObjectMapper mapper = new ObjectMapper();
//        Shop shop = null;
//        try {
//            shop = mapper.readValue(shopStr, Shop.class);
//        } catch (Exception e){
//            modelMap.put("success",false);
//            modelMap.put("errMsg",e.getMessage());
//            return modelMap;
//        }
//        CommonsMultipartFile shopImg = null;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        if(commonsMultipartResolver.isMultipart(request)){
//            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
//        }else {
//            modelMap.put("success",false);
//            modelMap.put("errMsg","上传图片不能为空");
//            return modelMap;
//        }
//        //2.注册店铺
//        if(shop != null && shopImg !=null){
//            UserInfo owner = new UserInfo();
//            owner.setUserId(1L);
//            shop.setUserInfo(owner);
//            File shopImgFile = new File(PathUtil.getImageBasePath()+ ImageUtil.getRandomFileName());
//            ShopExecution shopExecution = shopService.addShop(shop,shopImgFile);
//            if(shopExecution.getState()== ShopStateEnum.CHECK.getState()){
//                modelMap.put("success",true);
//            }else {
//                modelMap.put("success",false);
//                modelMap.put("errMsg",shopExecution.getStateInfo());
//                return modelMap;
//            }
//        }else {
//            modelMap.put("success",false);
//            modelMap.put("errMsg","请输入店铺信息");
//            return modelMap;
//        }
//        //3.返回结果
//    }


//    private static void inputStreamToFile(InputStream is, File file){
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(file);
//            int bytes = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytes = is.read(buffer)) != -1){
//                os.write(buffer,0,bytes);
//            }
//        }catch (IOException e){
//            throw new RuntimeException("调用isToFile异常"+e.getMessage());
//        }finally {
//            try {
//                if(os != null){
//                    os.close();
//                }
//                if(is != null){
//                    is.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("isToFile释放io异常"+e.getMessage());
//            }
//        }
//    }
}
