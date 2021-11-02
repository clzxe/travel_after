package com.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Strategy {
    private Integer strategyId;

    private Integer scenicId;

    private String strategyName;

    private String strategyImg;

    private String strategyCostMoney;

    private String strategyTravelDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date strategyDay;

    private Integer userId;

    private Integer isDelete;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private String strategyContent;

    private String userName;

    private String scenicName;

    private String userHead;

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyImg() {
        return strategyImg;
    }

    public void setStrategyImg(String strategyImg) {
        this.strategyImg = strategyImg;
    }

    public String getStrategyCostMoney() {
        return strategyCostMoney;
    }

    public void setStrategyCostMoney(String strategyCostMoney) {
        this.strategyCostMoney = strategyCostMoney;
    }

    public String getStrategyTravelDay() {
        return strategyTravelDay;
    }

    public void setStrategyTravelDay(String strategyTravelDay) {
        this.strategyTravelDay = strategyTravelDay;
    }

    public Date getStrategyDay() {
        return strategyDay;
    }

    public void setStrategyDay(Date strategyDay) {
        this.strategyDay = strategyDay;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getStrategyContent() {
        return strategyContent;
    }

    public void setStrategyContent(String strategyContent) {
        this.strategyContent = strategyContent;
    }
}