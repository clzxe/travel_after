package com.travel.travel.entity;

public class Hotels {
    private Integer hotelId;

    private String hotelName;

    private Integer hotelScenic;

    private String hotelAddr;

    private String hotelPhone;

    private String hotelImg;

    private Integer isDelete;

    private String scenicName;

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getHotelScenic() {
        return hotelScenic;
    }

    public void setHotelScenic(Integer hotelScenic) {
        this.hotelScenic = hotelScenic;
    }

    public String getHotelAddr() {
        return hotelAddr;
    }

    public void setHotelAddr(String hotelAddr) {
        this.hotelAddr = hotelAddr;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}