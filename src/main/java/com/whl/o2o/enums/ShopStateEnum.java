package com.whl.o2o.enums;

/**
 * 店铺状态枚举类
 */
public enum ShopStateEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOP_ID(-1002,"ShopID为空"),NULL_SHOP(-1003,"Shop为空"),
    NULL_AREA(-1004,"Area为空"),NULL_USER(-1005,"Owner为空"),NULL_SHOP_CATEGORY(-1006,"Category为空");

    private int state;
    private String stateInfo;

    /**
     * 构造器为私有 不希望外部调用该构造器对ShopState的枚举进行创建
     * @param state
     * @param stateInfo
     */
    private ShopStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state返回响应的枚举
     * @param state
     * @return
     */
    public static ShopStateEnum stateOf(int state){
        //遍历所有的枚举 如果存在符合的则返回
        for(ShopStateEnum stateEnum:values()){
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
