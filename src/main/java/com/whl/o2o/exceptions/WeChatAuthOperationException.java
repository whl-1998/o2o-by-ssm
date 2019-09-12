package com.whl.o2o.exceptions;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class WeChatAuthOperationException extends RuntimeException{

    private static final long serialVersionUID = -7336960777793411752L;

    public WeChatAuthOperationException(String msg){
        super(msg);//RuntimeException(msg)
    }
}
