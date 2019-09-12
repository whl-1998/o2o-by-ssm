package com.whl.o2o.web.superadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping("/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping("/shoplist")
    private String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    private String shopManagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement")
    private String productCategoryManagement(){
        return "shop/productcategorymanagement";
    }

    @RequestMapping("/productoperation")
    private String productOperation(){
        return  "shop/productoperation";
    }

    @RequestMapping("/productmanagement")
    private String productManagement(){
        return "shop/productmanagement";
    }
}