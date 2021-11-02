package com.travel.travel.entity;

import java.util.List;

public class Scenics {
    private Integer scenicId;

    private String scenicName;

    private String scenicOpenTime;

    private String scenicDescription;

    private String scenicCostTime;

    private String scenicTel;

    private String scenicAddr;

    private Integer scenicProvince;

    private Integer scenicCity;

    private String scenicImg;

    private String provinceName;

    private String cityName;

    private Integer isDelete;

    private Integer collectCount;

    private Double scenicPrice;

    private String acceptPeople;

    private String acceptSeason;

    private String scenicType;

    public String getScenicType() {
        return scenicType;
    }

    public void setScenicType(String scenicType) {
        this.scenicType = scenicType;
    }

    public Double getScenicPrice() {
        return scenicPrice;
    }

    public void setScenicPrice(Double scenicPrice) {
        this.scenicPrice = scenicPrice;
    }

    public String getAcceptPeople() {
        return acceptPeople;
    }

    public void setAcceptPeople(String acceptPeople) {
        this.acceptPeople = acceptPeople;
    }

    public String getAcceptSeason() {
        return acceptSeason;
    }

    public void setAcceptSeason(String acceptSeason) {
        this.acceptSeason = acceptSeason;
    }

    private double seem;

    public double getSeem() {
        return seem;
    }

    public void setSeem(double seem) {
        this.seem = seem;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicOpenTime() {
        return scenicOpenTime;
    }

    public void setScenicOpenTime(String scenicOpenTime) {
        this.scenicOpenTime = scenicOpenTime;
    }

    public String getScenicDescription() {
        return scenicDescription;
    }

    public void setScenicDescription(String scenicDescription) {
        this.scenicDescription = scenicDescription;
    }

    public String getScenicCostTime() {
        return scenicCostTime;
    }

    public void setScenicCostTime(String scenicCostTime) {
        this.scenicCostTime = scenicCostTime;
    }

    public String getScenicTel() {
        return scenicTel;
    }

    public void setScenicTel(String scenicTel) {
        this.scenicTel = scenicTel;
    }

    public String getScenicAddr() {
        return scenicAddr;
    }

    public void setScenicAddr(String scenicAddr) {
        this.scenicAddr = scenicAddr;
    }

    public Integer getScenicProvince() {
        return scenicProvince;
    }

    public void setScenicProvince(Integer scenicProvince) {
        this.scenicProvince = scenicProvince;
    }

    public Integer getScenicCity() {
        return scenicCity;
    }

    public void setScenicCity(Integer scenicCity) {
        this.scenicCity = scenicCity;
    }

    public String getScenicImg() {
        return scenicImg;
    }

    public void setScenicImg(String scenicImg) {
        this.scenicImg = scenicImg;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}