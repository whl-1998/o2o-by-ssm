package com.whl.o2o.dto;

import com.whl.o2o.entity.WeChatAuth;
import com.whl.o2o.enums.WeChatAuthStateEnum;

import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class WeChatAuthExecution {
    private int state;//结果状态
    private String stateInfo; //状态标识,用于解释结果状态
    private int count;
    private WeChatAuth weChatAuth;
    private List<WeChatAuth> weChatAuthList;

    public WeChatAuthExecution() {
    }

    //操作失败的时候使用的构造器,只返回结果状态和标识
    public WeChatAuthExecution(WeChatAuthStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器  增删改
    public WeChatAuthExecution(WeChatAuthStateEnum stateEnum,WeChatAuth weChatAuth){
        this.stateInfo = stateEnum.getStateInfo();
        this.state = stateEnum.getState();
        this.weChatAuth = weChatAuth;
    }

    //成功的构造器  查询
    public WeChatAuthExecution(WeChatAuthStateEnum stateEnum,List<WeChatAuth> weChatAuthList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.weChatAuthList = weChatAuthList;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WeChatAuth getWeChatAuth() {
        return weChatAuth;
    }

    public void setWeChatAuth(WeChatAuth weChatAuth) {
        this.weChatAuth = weChatAuth;
    }

    public List<WeChatAuth> getWeChatAuthList() {
        return weChatAuthList;
    }

    public void setWeChatAuthList(List<WeChatAuth> weChatAuthList) {
        this.weChatAuthList = weChatAuthList;
    }
}
