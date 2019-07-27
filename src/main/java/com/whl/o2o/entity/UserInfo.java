package com.whl.o2o.entity;

import java.util.Date;
import java.util.PrimitiveIterator;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class UserInfo {
    private Long userId;
    private String name;
    private String profileImg;//头像
    private String email;
    private String gender;
    private Integer enableStatus;//判断是否能正常登陆 0：禁止使用 1：正常
    private Integer userType;//区分用户属性 1：顾客 2：店家 3：超级管理员
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
