package com.whl.o2o.exceptions;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ProductOperationException extends RuntimeException {
    private static final long serialVersionUID = 7049712757622877009L;

    public ProductOperationException(String msg){
        super(msg);
    }
}
