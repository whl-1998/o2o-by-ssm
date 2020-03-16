package com.whl.o2o.dto;


/**
 * @author whl
 * @version V1.0
 * @Title:用于封装json对象
 * @Description:
 */

public class Result<T> {
    private boolean success;//成功标识
    private T data;//返回的数据
    private String errorMsg;//错误信息
    private int errorCode;

    public Result(){
    }

    //成功的构造器
    public Result(boolean success,T data){
        this.success = success;
        this.data = data;
    }

    //失败的构造器
    public Result(boolean success,String errorMsg,int errorCode){
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
