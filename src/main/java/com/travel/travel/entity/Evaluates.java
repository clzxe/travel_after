package com.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Evaluates {
    private Integer evaluateId;

    private String orderId;

    private Integer userId;

    private Integer evaluateLevel;

    private Integer hotelLevel;

    private String evaluateContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private Integer isDelete;

    private String userName;

    private String orderName;

    private List<E_reply> replyList;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderCreatTime;

    private Double orderSingelPrice;

    private Integer orderCount;

    private String orderImg;

    private String userHead;

    public Integer getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(Integer hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Date getOrderCreatTime() {
        return orderCreatTime;
    }

    public void setOrderCreatTime(Date orderCreatTime) {
        this.orderCreatTime = orderCreatTime;
    }

    public Double getOrderSingelPrice() {
        return orderSingelPrice;
    }

    public void setOrderSingelPrice(Double orderSingelPrice) {
        this.orderSingelPrice = orderSingelPrice;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getOrderImg() {
        return orderImg;
    }

    public void setOrderImg(String orderImg) {
        this.orderImg = orderImg;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<E_reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<E_reply> replyList) {
        this.replyList = replyList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
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