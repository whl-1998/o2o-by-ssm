package com.whl.o2o.exceptions;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID = 7242040263610088217L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
