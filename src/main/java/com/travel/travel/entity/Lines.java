package com.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Lines {
    private Integer lineId;

    private String lineTitle;

    private String lineDescription;

    private String lineScenic;

    private String linePhoto;

    private Double lineTotalTime;

    private String themeId;

    private Integer hotelId;

    private Integer isIncludeHotel;

    private Integer roomId;

    private Integer isDelete;

    private Integer cityId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lineCreatTime;

    private String scenicName;

    private String themeName;

    private String cityName;

    private String provinceName;

    private String className;

    private Hotel_rooms hotel_room;

    private Hotels hotel;

    private int count;
    private double weight;

    private Double roomPrice;

    private Double linePrice;

    private List<Scenics> scenicList;

    public List<Scenics> getScenicList() {
        return scenicList;
    }

    public void setScenicList(List<Scenics> scenicList) {
        this.scenicList = scenicList;
    }

    public Double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(Double linePrice) {
        this.linePrice = linePrice;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Hotel_rooms getHotel_room() {
        return hotel_room;
    }

    public void setHotel_room(Hotel_rooms hotel_room) {
        this.hotel_room = hotel_room;
    }

    public Hotels getHotel() {
        return hotel;
    }

    public void setHotel(Hotels hotel) {
        this.hotel = hotel;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineTitle() {
        return lineTitle;
    }

    public void setLineTitle(String lineTitle) {
        this.lineTitle = lineTitle;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getLineScenic() {
        return lineScenic;
    }

    public void setLineScenic(String lineScenic) {
        this.lineScenic = lineScenic;
    }

    public String getLinePhoto() {
        return linePhoto;
    }

    public void setLinePhoto(String linePhoto) {
        this.linePhoto = linePhoto;
    }

    public Double getLineTotalTime() {
        return lineTotalTime;
    }

    public void setLineTotalTime(Double lineTotalTime) {
        this.lineTotalTime = lineTotalTime;
    }

   public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }


    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getIsIncludeHotel() {
        return isIncludeHotel;
    }

    public void setIsIncludeHotel(Integer isIncludeHotel) {
        this.isIncludeHotel = isIncludeHotel;
    }

    public Date getLineCreatTime() {
        return lineCreatTime;
    }

    public void setLineCreatTime(Date lineCreatTime) {
        this.lineCreatTime = lineCreatTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}