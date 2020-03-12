package com.whl.o2o.exceptions;


/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ShopOperationException extends RuntimeException {
    private static final long serialVersionUID = 579561171276230897L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
