package com.whl.o2o.enums;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public enum  ProductStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"内部系统错误"),EMPTY_LIST(-1002,"添加数组为空"),EMPTY(-1003,"添加对象为空");

    private int state;
    private String stateInfo;

    /**
     * 构造器为私有 不希望外部调用该构造器对ProductCategoryStateEnum的枚举进行创建
     * @param state
     * @param stateInfo
     */
    private ProductStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state返回响应的枚举
     * @param state
     * @return
     */
    public static ProductStateEnum stateOf(int state){
        //遍历所有的枚举 如果存在符合的则返回
        for(ProductStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
