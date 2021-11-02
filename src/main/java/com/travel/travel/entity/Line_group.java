package com.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Line_group {
    private Integer id;

    private Integer lineId;

    private Integer groupId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lineStartTime;

    private Integer leaderId;

    private Integer linePeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date CreateTime;

    private String groupName;

    private String leaderName;

    private String lineName;

    private Integer cityId;

    private Double linePrice;

    private String provinceName;

    private String cityName;

    private String lineDescription;

    private String lineScenic;

    private String linePhoto;

    private Double lineTotalTime;

    private String themeId;

    private Integer hotelId;

    private Integer isIncludeHotel;

    private Integer roomId;

    private Double roomPrice;

    private List<Scenics> scenicList;

    public List<Scenics> getScenicList() {
        return scenicList;
    }

    public void setScenicList(List<Scenics> scenicList) {
        this.scenicList = scenicList;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lineCreatTime;

    private String scenicName;

    private String themeName;

    private String className;

    private Integer isDelete;

    private Hotel_rooms hotel_room;

    private Hotels hotel;

    private Double scenicPrice;

    private String acceptPeople;

    private String acceptSeason;

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

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getLineCreatTime() {
        return lineCreatTime;
    }

    public void setLineCreatTime(Date lineCreatTime) {
        this.lineCreatTime = lineCreatTime;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(Double linePrice) {
        this.linePrice = linePrice;
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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getLineStartTime() {
        return lineStartTime;
    }

    public void setLineStartTime(Date lineStartTime) {
        this.lineStartTime = lineStartTime;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getLinePeople() {
        return linePeople;
    }

    public void setLinePeople(Integer linePeople) {
        this.linePeople = linePeople;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}