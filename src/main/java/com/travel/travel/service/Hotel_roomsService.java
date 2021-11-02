package com.travel.travel.service;

import com.travel.travel.entity.Hotel_rooms;

import java.util.List;

public interface Hotel_roomsService {
    boolean deleteByPrimaryKey(Integer hotelRoomId);

    boolean insert(Hotel_rooms record);

    boolean insertSelective(Hotel_rooms record);

    Hotel_rooms selectByPrimaryKey(Integer hotelRoomId);

    boolean updateByPrimaryKeySelective(Hotel_rooms record);

    boolean updateByPrimaryKey(Hotel_rooms record);

    List<Hotel_rooms> getRoomByHotelId(Integer hotelId);
}
