package com.travel.travel.repository;

import com.travel.travel.entity.Hotel_rooms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Hotel_roomsMapper {
    int deleteByPrimaryKey(Integer hotelRoomId);

    int insert(Hotel_rooms record);

    int insertSelective(Hotel_rooms record);

    Hotel_rooms selectByPrimaryKey(Integer hotelRoomId);

    int updateByPrimaryKeySelective(Hotel_rooms record);

    int updateByPrimaryKey(Hotel_rooms record);

    List<Hotel_rooms> getRoomByHotelId(Integer hotelId);
}