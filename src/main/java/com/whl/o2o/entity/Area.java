package com.whl.o2o.entity;

import java.util.Date;

/**
 * @author whl
 * @version V1.0
 * @Title:区域实体类对象
 * @Description:
 */
public class Area {
    private Integer areaId;//ID
    private String areaName;//Name
    private Integer priority;//权重
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
