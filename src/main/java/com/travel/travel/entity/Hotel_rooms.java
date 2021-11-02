package com.travel.travel.entity;

public class Hotel_rooms {
    private Integer hotelRoomId;

    private Integer hotelId;

    private String hotelRoomName;

    private Double hotelRoomPrice;

    private Integer hotelRoomCount;

    private String hotelRoomImg;

    private Integer isDelete;

    public Integer getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(Integer hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelRoomName() {
        return hotelRoomName;
    }

    public void setHotelRoomName(String hotelRoomName) {
        this.hotelRoomName = hotelRoomName;
    }

    public Double getHotelRoomPrice() {
        return hotelRoomPrice;
    }

    public void setHotelRoomPrice(Double hotelRoomPrice) {
        this.hotelRoomPrice = hotelRoomPrice;
    }

    public Integer getHotelRoomCount() {
        return hotelRoomCount;
    }

    public void setHotelRoomCount(Integer hotelRoomCount) {
        this.hotelRoomCount = hotelRoomCount;
    }

    public String getHotelRoomImg() {
        return hotelRoomImg;
    }

    public void setHotelRoomImg(String hotelRoomImg) {
        this.hotelRoomImg = hotelRoomImg;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}