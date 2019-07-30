package com.whl.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whl
 * @version V1.0
 * @Title: 判断验证码是否符合预期
 * @Description:
 */
public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        //从会话中获取预期要得到的验证码
        String verifyCodeExpected = (String)request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        //实际输入的代码 这里调用了之前编写的工具类用于获取请求中key为verifyCodeActual的value并转换为string类型
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        //比较两个验证码是否一致
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
}
