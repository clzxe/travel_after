package com.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Shopping_cart {
    private Integer scId;

    private Integer lineGroupId;

    private Integer userId;

    private Integer scCount;

    private Double scSingelPrice;

    private String lineName;

    private String linePhoto;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Integer isDelete;

    private Integer roomCount;

    private Integer lineId;

    private Integer roomId;

    private Integer hotelId;

    private Double lineTotalTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lineStartTime;

    private Double roomPrice;

    private Integer isIncludeScenic;

    private Double scenicPrice;

    private String lineScenic;

    public String getLineScenic() {
        return lineScenic;
    }

    public void setLineScenic(String lineScenic) {
        this.lineScenic = lineScenic;
    }

    public Integer getIsIncludeScenic() {
        return isIncludeScenic;
    }

    public void setIsIncludeScenic(Integer isIncludeScenic) {
        this.isIncludeScenic = isIncludeScenic;
    }

    public Double getScenicPrice() {
        return scenicPrice;
    }

    public void setScenicPrice(Double scenicPrice) {
        this.scenicPrice = scenicPrice;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getLineStartTime() {
        return lineStartTime;
    }

    public void setLineStartTime(Date lineStartTime) {
        this.lineStartTime = lineStartTime;
    }

    public Double getLineTotalTime() {
        return lineTotalTime;
    }

    public void setLineTotalTime(Double lineTotalTime) {
        this.lineTotalTime = lineTotalTime;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getRoomCount() {
        return roomCount;
    }


    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLinePhoto() {
        return linePhoto;
    }

    public void setLinePhoto(String linePhoto) {
        this.linePhoto = linePhoto;
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getLineGroupId() {
        return lineGroupId;
    }

    public void setLineGroupId(Integer lineGroupId) {
        this.lineGroupId = lineGroupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScCount() {
        return scCount;
    }

    public void setScCount(Integer scCount) {
        this.scCount = scCount;
    }

    public Double getScSingelPrice() {
        return scSingelPrice;
    }

    public void setScSingelPrice(Double scSingelPrice) {
        this.scSingelPrice = scSingelPrice;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}