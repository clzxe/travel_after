package com.travel.travel.entity;

import java.util.Date;

public class Collect {
    private Integer collectId;

    private Integer userId;

    private Integer collectType;

    private Integer scenicStrategy;

    private Date createTime;

    private Integer isDelete;

    private String userName;

    private String typeName;

    private Integer countNum;

    private String collectPhoto;

    public String getCollectPhoto() {
        return collectPhoto;
    }

    public void setCollectPhoto(String collectPhoto) {
        this.collectPhoto = collectPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }

    public Integer getScenicStrategy() {
        return scenicStrategy;
    }

    public void setScenicStrategy(Integer scenicStrategy) {
        this.scenicStrategy = scenicStrategy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}