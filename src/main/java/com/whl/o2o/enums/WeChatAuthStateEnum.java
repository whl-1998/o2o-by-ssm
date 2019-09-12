package com.whl.o2o.enums;


/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public enum  WeChatAuthStateEnum {
    OPEN_ID_FAIL(-1,"openId错误"),SUCCESS(1,"操作成功"),NULL_AUTH_INFO(-10,"WeChatAuth为空");


    private int state;
    private String stateInfo;

    /**
     * 构造器为私有 不希望外部调用该构造器对ShopState的枚举进行创建
     * @param state
     * @param stateInfo
     */
    private WeChatAuthStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state返回响应的枚举
     * @param state
     * @return
     */
    public static WeChatAuthStateEnum stateOf(int state){
        //遍历所有的枚举 如果存在符合的则返回
        for(WeChatAuthStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
