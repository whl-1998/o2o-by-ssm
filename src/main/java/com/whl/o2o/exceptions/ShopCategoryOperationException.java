package com.whl.o2o.exceptions;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ShopCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID = -3515089628331431912L;

    public ShopCategoryOperationException(String msg) {
        super(msg);
    }
}
