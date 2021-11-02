package com.travel.travel.entity;

public class Travel_time {
    private Integer travelTimeId;

    private Integer travelTimeLineId;

    private String travelTimeTime;

    private String travelTimeFood;

    private String travelTimeActivity;

    private String travelTimeHotel;

    private Integer isDelete;

    private Integer rowNum;

    public Integer getTravelTimeId() {
        return travelTimeId;
    }

    public void setTravelTimeId(Integer travelTimeId) {
        this.travelTimeId = travelTimeId;
    }

    public Integer getTravelTimeLineId() {
        return travelTimeLineId;
    }

    public void setTravelTimeLineId(Integer travelTimeLineId) {
        this.travelTimeLineId = travelTimeLineId;
    }

    public String getTravelTimeTime() {
        return travelTimeTime;
    }

    public void setTravelTimeTime(String travelTimeTime) {
        this.travelTimeTime = travelTimeTime;
    }

    public String getTravelTimeFood() {
        return travelTimeFood;
    }

    public void setTravelTimeFood(String travelTimeFood) {
        this.travelTimeFood = travelTimeFood;
    }

    public String getTravelTimeActivity() {
        return travelTimeActivity;
    }

    public void setTravelTimeActivity(String travelTimeActivity) {
        this.travelTimeActivity = travelTimeActivity;
    }

    public String getTravelTimeHotel() {
        return travelTimeHotel;
    }

    public void setTravelTimeHotel(String travelTimeHotel) {
        this.travelTimeHotel = travelTimeHotel;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }
}