package com.whl.o2o.exceptions;


/**
 * @author whl
 * @version V1.0
 * @Title:封装Shop操作的异常
 * @Description:该业务异常类必须继承RuntimeException，否则抛出异常事务无法进行回滚
 */
public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg){
        super(msg);//RuntimeException(msg)
    }
}
